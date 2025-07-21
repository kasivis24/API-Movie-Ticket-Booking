package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.BookingHistory;
import com.incubation.movieticketbooking.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/v1/booking-history")
public class BookingHistoryServlet extends HttpServlet {


    private final AppDb appDb = AppDb.getInstance();

    public BookingHistoryServlet() throws SQLException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String userUid = Utils.getAuthToken(req.getCookies());

        try {
            List<BookingHistory> bookingHistoryList = appDb.getUserBookingHistory(userUid);
            out.print(new Gson().toJson(bookingHistoryList));
        } catch (SQLException e) {
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            throw new RuntimeException(e);
        }

    }


}
