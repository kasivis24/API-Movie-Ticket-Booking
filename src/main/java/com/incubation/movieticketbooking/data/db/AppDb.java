package com.incubation.movieticketbooking.data.db;

import com.incubation.movieticketbooking.data.dbconfig.DBConnection;
import com.incubation.movieticketbooking.data.dto.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppDb {
    private static AppDb instance = null;
    private static Connection conn;

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
        preparedStatement.setString(1,userUid);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String movieName = resultSet.getString("movie_name");
            String theatreName = resultSet.getString("theatre_name");
            int numberOfSeats = resultSet.getInt("no_of_seats");
            int amount = resultSet.getInt("total_amount");

            bookingHistoryList.add(new BookingHistory(movieName,theatreName,numberOfSeats,amount));
        }
        return bookingHistoryList;
    }

    public boolean uploadShow (Show show) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int showId = 0;

        try {

            conn.setAutoCommit(false);
            String query = "INSERT INTO shows (movie_id,theatre_id,show_time,base_price,available_seats) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,show.getMovieId());
            stmt.setInt(2,show.getTheatreId());
            stmt.setString(3,show.getDateTime());
            stmt.setInt(4,show.getBasePrice());
            stmt.setInt(5,show.getAvailableSeat());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs.next()){
                showId = rs.getInt(1);
            }
            rs.close();
            stmt.close();

            String queryFillSeats = "INSERT INTO Seats (show_id, seat_number, price, is_booked) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(queryFillSeats);
            for (int i = 1; i <= show.getAvailableSeat(); i++) {
                stmt.setInt(1,showId);
                stmt.setString(2,""+i);
                stmt.setInt(3,show.getBasePrice());
                stmt.setBoolean(4,false);
                stmt.addBatch();
            }

            stmt.executeBatch();
            stmt.close();

            conn.commit();

        }catch (SQLException ex){
            conn.rollback();
            return false;
        }finally {
            if (stmt != null) stmt.close();
            conn.setAutoCommit(true);
        }

        return true;
    }

    public boolean uploadTheatre (Theatre theatre) throws SQLException {
        String query = "INSERT INTO theatres (name , location) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,theatre.getTheatreName());
        statement.setString(2,theatre.getTheatreLocation());

        statement.executeUpdate();

        return true;

    }


    public boolean uploadMovie (Movie movie) throws SQLException {


        String query = "INSERT INTO movies (title, genre, duration, language, description, image_url) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,movie.getMovieTitle());
        stmt.setString(2,movie.getMovieGenre());
        stmt.setInt(3,movie.getDuration());
        stmt.setString(4,movie.getMovieLang());
        stmt.setString(5,movie.getMovieDesc());
        stmt.setString(6,movie.getMovieThumbnail());
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

    public boolean loginVerifyAdmin(LoginInfo loginInfo) throws  SQLException{
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


    public boolean addAdminData(String adminUid,String adminName,String adminMail,String adminPassword) throws SQLException {

        String hashedPassword = BCrypt.hashpw(adminPassword, BCrypt.gensalt(12));

        String query = "INSERT INTO adminData (adminUid, adminName, adminMail, adminPassword) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,adminUid);
        stmt.setString(2,adminName);
        stmt.setString(3,adminMail);
        stmt.setString(4,hashedPassword);
        stmt.executeUpdate();


        return true;

    }



    public static boolean payNow(String userUid, int movieId, int theaterId, int showId, List<String> seatNames) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int bookingId = 0;
        int seatCount = seatNames.size();
        int totalPrice = 0;
        List<Integer> seatIds = new ArrayList<>();
//        userUid = "8fd0efd1-58d3-4670-bf65-9cacc0b85887";

        try {
            // Start transaction
            conn.setAutoCommit(false);

            // 1. Convert seatNames -> seatIds
            String placeholders = String.join(",", Collections.nCopies(seatNames.size(), "?"));
            String query = "SELECT seat_id, price FROM Seats WHERE show_id = ? AND seat_number IN (" + placeholders + ")";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, showId);
            for (int i = 0; i < seatNames.size(); i++) {
                stmt.setString(i + 2, seatNames.get(i));
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                seatIds.add(rs.getInt("seat_id"));
                totalPrice += rs.getInt("price");
            }

            if (seatIds.size() != seatNames.size()) {
                return false;
            }

            rs.close();
            stmt.close();

            // 2. Insert into Bookings
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

            // 3. Insert into BookingDetails
            String insertDetails = "INSERT INTO BookingDetails (booking_id, theater_id, movie_id, seat_count, price) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertDetails);
            stmt.setInt(1, bookingId);
            stmt.setInt(2, theaterId);
            stmt.setInt(3, movieId);
            stmt.setInt(4, seatCount);
            stmt.setInt(5, totalPrice);
            stmt.executeUpdate();
            stmt.close();

            // 4. Insert into SeatAllotment
            String insertAllotment = "INSERT INTO SeatAllotment (booking_id, seat_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertAllotment);
            for (int seatId : seatIds) {
                stmt.setInt(1, bookingId);
                stmt.setInt(2, seatId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            stmt.close();

            // 5. Update Seats to mark as booked
            String seatIdPlaceholders = String.join(",", Collections.nCopies(seatIds.size(), "?"));
            String updateSeats = "UPDATE Seats SET is_booked = TRUE WHERE seat_id IN (" + seatIdPlaceholders + ")";
            stmt = conn.prepareStatement(updateSeats);
            for (int i = 0; i < seatIds.size(); i++) {
                stmt.setInt(i + 1, seatIds.get(i));
            }
            stmt.executeUpdate();
            stmt.close();

            conn.commit();
            System.out.println("Booking successful. Booking ID: " + bookingId);

        } catch (SQLException ex) {
            conn.rollback();
            System.err.println("Transaction failed. Rolling back. Error: " + ex.getMessage());
            return false;
        } finally {
            if (stmt != null) stmt.close();
            conn.setAutoCommit(true);
        }

        return true;
    }

    public List<Seat> getSeats(int showId) throws SQLException {

        String getTotalSeatQuery =  "SELECT " +
                "COUNT(*) AS total_seats, " +
                "SUM(CASE WHEN is_booked = TRUE THEN 1 ELSE 0 END) AS booked_seats, " +
                "SUM(CASE WHEN is_booked = FALSE THEN 1 ELSE 0 END) AS unbooked_seats " +
                "FROM Seats " +
                "WHERE show_id = ?";

        String seatDetailsQuery = "SELECT " +
                "seat_id, seat_number, is_booked " +
                "FROM Seats " +
                "WHERE show_id = ?";


        PreparedStatement totalPrepareStatement = conn.prepareStatement(getTotalSeatQuery);
        PreparedStatement seatsDetailsPrepareStatement = conn.prepareStatement(seatDetailsQuery);

        totalPrepareStatement.setInt(1,showId);
        seatsDetailsPrepareStatement.setInt(1,showId);

        ResultSet rsTotal = totalPrepareStatement.executeQuery();
        ResultSet rsSeatDetails = seatsDetailsPrepareStatement.executeQuery();

        int seatCount = 0;

        while (rsTotal.next()){
            seatCount = rsTotal.getInt("total_seats");
        }

        System.out.println(seatCount);

        List<Seat> seatList = new ArrayList<>(Collections.nCopies(seatCount,null));

        while (rsSeatDetails.next()){
            seatList.set(Integer.valueOf(rsSeatDetails.getString("seat_number"))-1,new Seat(rsSeatDetails.getInt("seat_id"),rsSeatDetails.getString("seat_number"),rsSeatDetails.getInt("is_booked")));
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

        preparedStatementForMovie.setInt(1,movieId);
        preparedStatementForTheatre.setInt(1,movieId);


        ResultSet rsMovie = preparedStatementForMovie.executeQuery();
        ResultSet rsTheatre = preparedStatementForTheatre.executeQuery();

        System.out.println(movieId);


        while(rsMovie.next()){
            int showId = rsMovie.getInt("movie_id");
            String movieName = rsMovie.getString("title");
            String movieGenre = rsMovie.getString("genre");
            int movieDuration = rsMovie.getInt("duration");
            String movieThumbnail = rsMovie.getString("image_url");
            String movieLang = rsMovie.getString("language");
            String movieDesc = rsMovie.getString("description");
            movie = new Movie(showId, movieName, movieGenre, movieDuration, movieThumbnail,movieLang, movieDesc);
        }

        while (rsTheatre.next()){
            Theatre theatre = new Theatre(rsTheatre.getInt("theatre_id"),rsTheatre.getString("name"),rsTheatre.getString("location"));
            theatreList.add(theatre);
        }

        return new MovieTheatreMapping(movie,theatreList);
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

                Movie show = new Movie(showId, movieName, movieGenre, movieDuration, movieThumbnail,movieLang, movieDesc);
                showList.add(show);
            }

        return showList;
    }

    public void addUserData(UserInfo userData) throws SQLException {

        String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt(12));

        String sql = "INSERT INTO movieUserData (userUid, username, email, password) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,userData.getUserUid());
        stmt.setString(2,userData.getName());
        stmt.setString(3,userData.getEmail());
        stmt.setString(4,hashedPassword);
        stmt.executeUpdate();
    }

    public boolean loginVerify(LoginInfo loginInfo) throws  SQLException{
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
            userProfileInfo = new UserProfileInfo(rs.getString("username"),rs.getString("email"));
        }
        return userProfileInfo;
    }

    public List<Show> getShowsByTheatre(int movieId,int theatreId) throws SQLException {

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
        preparedStatement.setInt(1,movieId);
        preparedStatement.setInt(2,theatreId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int showId = resultSet.getInt("show_id");
            Timestamp showTime = resultSet.getTimestamp("show_time");
            double basePrice = resultSet.getDouble("base_price");
            int availableSeats = resultSet.getInt("available_seats");
            String movieTitle = resultSet.getString("title");
            String theatreName = resultSet.getString("name");
            String location = resultSet.getString("location");

            LocalDateTime showDateTime = showTime.toLocalDateTime();
            showList.add(new Show(showId,movieId,theatreId,showDateTime.toString(),(int) basePrice,availableSeats,movieTitle,theatreName,location));
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
            theatreList.add(new Theatre(theatreId,name,location));
        }

        statement.close();
        resultSet.close();

        return theatreList;
    }
}
