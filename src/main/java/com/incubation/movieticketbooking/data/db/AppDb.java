package com.incubation.movieticketbooking.data.db;

import com.incubation.movieticketbooking.data.dbconfig.DBConnection;
import com.incubation.movieticketbooking.data.dto.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AppDb {

    private static AppDb instance = null;
    private static Connection conn;
    private static final ConcurrentHashMap<String, Object> seatLocks = new ConcurrentHashMap<>();


    private AppDb() throws SQLException {
        try {
            System.out.println("Fun");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Now ");
            conn = DBConnection.getConnection();
            System.out.println("Now ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static AppDb getInstance() throws SQLException {
        if (instance == null) instance = new AppDb();
        return instance;
    }


    public List<BookingHistory> getUserBookingHistory(String userUid) throws SQLException {

        String query = "SELECT " +
                "m.title AS movie_name, " +
                "t.name AS theatre_name, " +
                "bd.seat_count AS no_of_seats, " +
                "bd.price AS total_amount, " +
                "b.booking_time " +
                "FROM " +
                "Bookings b " +
                "JOIN " +
                "BookingDetails bd ON b.booking_id = bd.booking_id " +
                "JOIN " +
                "movies m ON bd.movie_id = m.movie_id " +
                "JOIN " +
                "theatres t ON bd.theater_id = t.theatre_id " +
                "WHERE " +
                "b.userUid = ? " +
                "ORDER BY " +
                "b.booking_time DESC";

        List<BookingHistory> bookingHistoryList = new ArrayList<>();

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, userUid);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String movieName = resultSet.getString("movie_name");
            String theatreName = resultSet.getString("theatre_name");
            int numberOfSeats = resultSet.getInt("no_of_seats");
            int amount = resultSet.getInt("total_amount");

            bookingHistoryList.add(new BookingHistory(movieName, theatreName, numberOfSeats, amount));
        }
        return bookingHistoryList;
    }

    public boolean uploadShow(Show show) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int showId = 0;

        try {

            conn.setAutoCommit(false);
            String query = "INSERT INTO shows (movie_id,theatre_id,show_time,base_price,available_seats) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, show.getMovieId());
            stmt.setInt(2, show.getTheatreId());
            stmt.setString(3, show.getDateTime());
            stmt.setInt(4, show.getBasePrice());
            stmt.setInt(5, show.getAvailableSeat());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                showId = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            String queryFillSeats = "INSERT INTO Seats (show_id, seat_number, price, is_booked) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(queryFillSeats);

            for (int i = 1; i <= show.getAvailableSeat(); i++) {
                stmt.setInt(1, showId);
                stmt.setString(2, "" + i);
                stmt.setInt(3, show.getBasePrice());
                stmt.setInt(4, 2);
                stmt.addBatch();
            }

            stmt.executeBatch();
            stmt.close();

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            return false;
        } finally {
            if (stmt != null) stmt.close();
            conn.setAutoCommit(true);
        }

        return true;
    }

    public boolean uploadTheatre(Theatre theatre) throws SQLException {
        String query = "INSERT INTO theatres (name , location) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1, theatre.getTheatreName());
        statement.setString(2, theatre.getTheatreLocation());

        statement.executeUpdate();

        return true;

    }


    public boolean uploadMovie(Movie movie) throws SQLException {


        String query = "INSERT INTO movies (title, genre, duration, language, description, image_url) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, movie.getMovieTitle());
        stmt.setString(2, movie.getMovieGenre());
        stmt.setInt(3, movie.getDuration());
        stmt.setString(4, movie.getMovieLang());
        stmt.setString(5, movie.getMovieDesc());
        stmt.setString(6, movie.getMovieThumbnail());
        stmt.executeUpdate();


        return true;
    }


    public String getAdminUid(String email) throws SQLException {
        String sql = "SELECT * FROM adminData WHERE adminMail = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String adminUid = rs.getString("adminUid");
            return adminUid;
        }
        System.out.println("Invalid username or password");
        return "";
    }

    public boolean loginVerifyAdmin(LoginInfo loginInfo) throws SQLException {
        String sql = "SELECT * FROM adminData WHERE adminMail = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, loginInfo.getEmail());
        ResultSet rs = stmt.executeQuery();

        String enteredPassword = loginInfo.getPassword();

        if (rs.next()) {

            String hashedPassword = rs.getString("adminPassword");

            if (BCrypt.checkpw(enteredPassword, hashedPassword)) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("Invalid email or password");
        }
        return false;
    }


    public boolean addAdminData(String adminUid, String adminName, String adminMail, String adminPassword) throws SQLException {

        String hashedPassword = BCrypt.hashpw(adminPassword, BCrypt.gensalt(12));

        String query = "INSERT INTO adminData (adminUid, adminName, adminMail, adminPassword) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, adminUid);
        stmt.setString(2, adminName);
        stmt.setString(3, adminMail);
        stmt.setString(4, hashedPassword);
        stmt.executeUpdate();


        return true;

    }

    public boolean isAvailable(int showId, int seatNumber) {
        String checkIsAvailableQuery = "SELECT * FROM Seats WHERE show_id = ? AND seat_number = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(checkIsAvailableQuery);
            preparedStatement.setInt(1, showId);
            preparedStatement.setInt(2, seatNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String seatStatus = resultSet.getString("is_booked");
                switch (seatStatus) {
                    case "Available":
                        return true;
                    default:
                        return false;
                }
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }



    /*
    public boolean selectSeat(int showId, int seatNumber) {

        String seatKey = showId + "-" + seatNumber;
        seatLocks.putIfAbsent(seatKey, new Object());

        synchronized (seatLocks.get(seatKey)) {
            try {
                String selectQuery = "SELECT is_booked FROM Seats WHERE show_id = ? AND seat_number = ?";
                PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
                selectStmt.setInt(1, showId);
                selectStmt.setInt(2, seatNumber);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("is_booked");

                    if (status.equals("Available")) {
                        String updateQuery = "UPDATE Seats SET is_booked = 'TempNotAvailable' WHERE show_id = ? AND seat_number = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                        updateStmt.setInt(1, showId);
                        updateStmt.setInt(2, seatNumber);
                        updateStmt.executeUpdate();
                        startTimeoutToReleaseSeat(showId, seatNumber);
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    private void startTimeoutToReleaseSeat(int showId, int seatNumber) {

        new Thread(() -> {
            try {

                /// Must Have to pay this time

                Thread.sleep(10000);
                String checkQuery = "SELECT is_booked FROM Seats WHERE show_id = ? AND seat_number = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setInt(1, showId);
                checkStmt.setInt(2, seatNumber);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next() && rs.getString("is_booked").equals("TempNotAvailable")) {
                    String revertQuery = "UPDATE Seats SET is_booked = 'Available' WHERE show_id = ? AND seat_number = ?";
                    PreparedStatement revertStmt = conn.prepareStatement(revertQuery);
                    revertStmt.setInt(1, showId);
                    revertStmt.setInt(2, seatNumber);
                    revertStmt.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
     */

    public boolean payNow(String userUid, int movieId, int theaterId, int showId, List<String> seatNames) throws SQLException {

        List<String> sortedSeats = new ArrayList<>(seatNames);
        Collections.sort(sortedSeats);
        String seatKey = showId + "-" + String.join("-", sortedSeats);

        Object lock = seatLocks.computeIfAbsent(seatKey, k -> new Object());

        synchronized (lock) {
            try {
                return performBooking(userUid, movieId, theaterId, showId, sortedSeats);
            } finally {
                seatLocks.remove(seatKey);
            }
        }
    }


    private boolean performBooking(String userUid, int movieId, int theaterId, int showId, List<String> seatNames) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int bookingId = 0;
        int totalPrice = 0;
        List<Integer> seatIds = new ArrayList<>();

        try {
            conn.setAutoCommit(false);

            String placeholders = String.join(",", Collections.nCopies(seatNames.size(), "?"));
            String selectQuery = "SELECT seat_id, price, is_booked FROM Seats WHERE show_id = ? AND seat_number IN (" + placeholders + ") FOR UPDATE";
            stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, showId);
            for (int i = 0; i < seatNames.size(); i++) {
                stmt.setString(i + 2, seatNames.get(i));
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                String status = rs.getString("is_booked");
                if (!status.equalsIgnoreCase("Available")) {
                    conn.rollback();
                    System.out.println("One or more seats are already booked or temp unavailable.");
                    return false;
                }
                seatIds.add(rs.getInt("seat_id"));
                totalPrice += rs.getInt("price");
            }

            if (seatIds.size() != seatNames.size()) {
                conn.rollback();
                System.out.println("Some seat numbers not found.");
                return false;
            }

            rs.close();
            stmt.close();

            String insertBooking = "INSERT INTO Bookings (userUid) VALUES (?)";
            stmt = conn.prepareStatement(insertBooking, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userUid);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }
            rs.close();
            stmt.close();

            String insertDetails = "INSERT INTO BookingDetails (booking_id, theater_id, movie_id, seat_count, price) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertDetails);
            stmt.setInt(1, bookingId);
            stmt.setInt(2, theaterId);
            stmt.setInt(3, movieId);
            stmt.setInt(4, seatNames.size());
            stmt.setInt(5, totalPrice);
            stmt.executeUpdate();
            stmt.close();

            String insertAllotment = "INSERT INTO SeatAllotment (booking_id, seat_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertAllotment);
            for (int seatId : seatIds) {
                stmt.setInt(1, bookingId);
                stmt.setInt(2, seatId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            stmt.close();

            String updatePlaceholders = String.join(",", Collections.nCopies(seatIds.size(), "?"));
            String updateSeats = "UPDATE Seats SET is_booked = 'Booked' WHERE seat_id IN (" + updatePlaceholders + ")";
            stmt = conn.prepareStatement(updateSeats);
            for (int i = 0; i < seatIds.size(); i++) {
                stmt.setInt(i + 1, seatIds.get(i));
            }
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            System.out.println("✅ Booking successful. Booking ID: " + bookingId);
            return true;

        } catch (SQLException ex) {
            conn.rollback();
            System.err.println("❌ Booking failed. Rolled back. Error: " + ex.getMessage());
            return false;
        } finally {
            if (stmt != null) stmt.close();
            conn.setAutoCommit(true);
        }
    }


    public List<Seat> getSeats(int showId) throws SQLException {

        String getTotalSeatQuery = "SELECT " +
                "COUNT(*) AS total_seats, " +
                "SUM(CASE WHEN is_booked = 1 THEN 1 ELSE 0 END) AS booked_seats, " +
                "SUM(CASE WHEN is_booked = 2 THEN 1 ELSE 0 END) AS unbooked_seats " +
                "FROM Seats " +
                "WHERE show_id = ?";

        String seatDetailsQuery = "SELECT " +
                "seat_id, seat_number, is_booked " +
                "FROM Seats " +
                "WHERE show_id = ?";


        PreparedStatement totalPrepareStatement = conn.prepareStatement(getTotalSeatQuery);
        PreparedStatement seatsDetailsPrepareStatement = conn.prepareStatement(seatDetailsQuery);

        totalPrepareStatement.setInt(1, showId);
        seatsDetailsPrepareStatement.setInt(1, showId);

        ResultSet rsTotal = totalPrepareStatement.executeQuery();
        ResultSet rsSeatDetails = seatsDetailsPrepareStatement.executeQuery();

        int seatCount = 0;

        while (rsTotal.next()) {
            seatCount = rsTotal.getInt("total_seats");
        }

        System.out.println(seatCount);

        List<Seat> seatList = new ArrayList<>(Collections.nCopies(seatCount, null));

        while (rsSeatDetails.next()) {
            String status = rsSeatDetails.getString("is_booked");
            seatList.set(Integer.valueOf(rsSeatDetails.getString("seat_number")) - 1, new Seat(rsSeatDetails.getInt("seat_id"), rsSeatDetails.getString("seat_number"), status.equals("Available") ? 2 : status.equals("Booked") ? 1 : 3));
        }

        return seatList;


    }

    public MovieTheatreMapping getMovieTheatreById(int movieId) throws SQLException {
        List<Theatre> theatreList = new ArrayList<>();
        Movie movie = null;

        String movieQuery = "select * from movies where movie_id = ?";

        String theatreQuery = "SELECT DISTINCT t.theatre_id, t.name, t.location " +
                "FROM shows s " +
                "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                "WHERE s.movie_id = ?";


        PreparedStatement preparedStatementForMovie = conn.prepareStatement(movieQuery);
        PreparedStatement preparedStatementForTheatre = conn.prepareStatement(theatreQuery);

        preparedStatementForMovie.setInt(1, movieId);
        preparedStatementForTheatre.setInt(1, movieId);


        ResultSet rsMovie = preparedStatementForMovie.executeQuery();
        ResultSet rsTheatre = preparedStatementForTheatre.executeQuery();

        System.out.println(movieId);


        while (rsMovie.next()) {
            int showId = rsMovie.getInt("movie_id");
            String movieName = rsMovie.getString("title");
            String movieGenre = rsMovie.getString("genre");
            int movieDuration = rsMovie.getInt("duration");
            String movieThumbnail = rsMovie.getString("image_url");
            String movieLang = rsMovie.getString("language");
            String movieDesc = rsMovie.getString("description");
            movie = new Movie(showId, movieName, movieGenre, movieDuration, movieThumbnail, movieLang, movieDesc);
        }

        while (rsTheatre.next()) {
            Theatre theatre = new Theatre(rsTheatre.getInt("theatre_id"), rsTheatre.getString("name"), rsTheatre.getString("location"));
            theatreList.add(theatre);
        }

        return new MovieTheatreMapping(movie, theatreList);
    }


    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> showList = new ArrayList<>();
        String sql = "select * from movies";


        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int showId = rs.getInt("movie_id");
            String movieName = rs.getString("title");
            String movieGenre = rs.getString("genre");
            int movieDuration = rs.getInt("duration");
            String movieThumbnail = rs.getString("image_url");
            String movieLang = rs.getString("language");
            String movieDesc = rs.getString("description");

            Movie show = new Movie(showId, movieName, movieGenre, movieDuration, movieThumbnail, movieLang, movieDesc);
            showList.add(show);
        }

        return showList;
    }

    public void addUserData(UserInfo userData) throws SQLException {

        String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt(12));

        String sql = "INSERT INTO movieUserData (userUid, username, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userData.getUserUid());
        stmt.setString(2, userData.getName());
        stmt.setString(3, userData.getEmail());
        stmt.setString(4, hashedPassword);
        stmt.executeUpdate();
    }

    public boolean loginVerify(LoginInfo loginInfo) throws SQLException {
        String sql = "SELECT * FROM movieUserData WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, loginInfo.getEmail());
        ResultSet rs = stmt.executeQuery();

        String enteredPassword = loginInfo.getPassword();

        if (rs.next()) {

            String hashedPassword = rs.getString("password");

            if (BCrypt.checkpw(enteredPassword, hashedPassword)) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("Invalid username or password");
        }
        return false;
    }

    public String getUserUid(String email) throws SQLException {
        String sql = "SELECT * FROM movieUserData WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String userUid = rs.getString("userUid");
            return userUid;
        }
        System.out.println("Invalid username or password");
        return "";
    }

    public UserProfileInfo getUserProfile(String userUid) throws SQLException {
        String sql = "SELECT * FROM movieUserData WHERE userUid = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userUid);
        ResultSet rs = stmt.executeQuery();
        UserProfileInfo userProfileInfo = null;
        if (rs.next()) {
            userProfileInfo = new UserProfileInfo(rs.getString("username"), rs.getString("email"));
        }
        return userProfileInfo;
    }

    public List<Show> getShowsByTheatre(int movieId, int theatreId) throws SQLException {

        List<Show> showList = new ArrayList<>();

        String query = "SELECT " +
                "s.show_id, " +
                "s.show_time, " +
                "s.base_price, " +
                "s.available_seats, " +
                "m.title, " +
                "t.name, " +
                "t.location " +
                "FROM shows s " +
                "JOIN movies m ON s.movie_id = m.movie_id " +
                "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                "WHERE s.movie_id = ? AND s.theatre_id = ?";


        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, movieId);
        preparedStatement.setInt(2, theatreId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int showId = resultSet.getInt("show_id");
            Timestamp showTime = resultSet.getTimestamp("show_time");
            double basePrice = resultSet.getDouble("base_price");
            int availableSeats = resultSet.getInt("available_seats");
            String movieTitle = resultSet.getString("title");
            String theatreName = resultSet.getString("name");
            String location = resultSet.getString("location");

            LocalDateTime showDateTime = showTime.toLocalDateTime();
            showList.add(new Show(showId, movieId, theatreId, showDateTime.toString(), (int) basePrice, availableSeats, movieTitle, theatreName, location));
        }


        return showList;
    }


    public List<Theatre> getAllTheatre() throws SQLException {
        String query = "SELECT * FROM theatres";
        List<Theatre> theatreList = new ArrayList<>();

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int theatreId = resultSet.getInt("theatre_id");
            String name = resultSet.getString("name");
            String location = resultSet.getString("location");
            theatreList.add(new Theatre(theatreId, name, location));
        }

        statement.close();
        resultSet.close();

        return theatreList;
    }
}
