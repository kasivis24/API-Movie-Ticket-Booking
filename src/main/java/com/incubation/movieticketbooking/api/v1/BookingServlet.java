package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.db.RedisDb;
import com.incubation.movieticketbooking.data.dto.Booking;
import com.incubation.movieticketbooking.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/v1/booking")
public class BookingServlet extends HttpServlet {

    public final AppDb appDb = AppDb.getInstance();

    public final RedisDb redisDb = RedisDb.getInstance();


    public BookingServlet() throws SQLException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("application/json");

        Gson gson = new Gson();
        Booking booking = gson.fromJson(req.getReader(), Booking.class);
        PrintWriter out = resp.getWriter();

        System.out.println("Movie ID: " + booking.getMovieId());
        System.out.println("Theater ID: " + booking.getTheatreId());
        System.out.println("Seats: " + booking.getSeatIds());
        System.out.println(booking.getSeatIds());

        try {
            if (appDb.payNow(Utils.getAuthToken(req.getCookies()), booking.getMovieId(),booking.getTheatreId(),booking.getShowId(),booking.getSeatIds())){
                out.print("{\"status\":\"success\",\"message\":\"Booking received\"}");
            }else {
                out.print("{\"status\":\"error\", \"message\":\"Some seat numbers not found.\"}");
            }
        } catch (SQLException e) {
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            throw new RuntimeException(e);
        }


    }
}
