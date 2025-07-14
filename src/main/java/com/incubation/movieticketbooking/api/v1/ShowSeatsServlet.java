package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Seat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/v1/seats")
public class ShowSeatsServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public ShowSeatsServlet() throws SQLException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int showId = Integer.parseInt(request.getParameter("showId"));

        System.out.println("sdsdbs");

        response.setContentType("application/json");

        List<Seat> seatList = new ArrayList<>();

        try {
            seatList = appDb.getSeats(showId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(new Gson().toJson(seatList));
    }

}
