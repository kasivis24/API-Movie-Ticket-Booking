package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.MovieTheatreMapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/v1/movie/*")
public class MovieTheatreServlet extends HttpServlet {
    private final AppDb appDb = AppDb.getInstance();

    public MovieTheatreServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            System.out.println("1");
            out.print("{\"status\":\"error\", \"message\":\"path error occurred.\"}");
            return;
        }

        String movieIdStr = pathInfo.substring(1);
        int movieId;

        try {
            movieId = Integer.parseInt(movieIdStr);
        } catch (NumberFormatException e) {
            System.out.println("number ");
            out.print("{\"status\":\"error\", \"message\":\"path error occurred.\"}");
            return;
        }
        try {
            MovieTheatreMapping movieTheatreMapping = appDb.getMovieTheatreById(movieId);
            String responseJson = new Gson().toJson(movieTheatreMapping);
            out.print(responseJson);
        } catch (SQLException e) {
            System.out.println("hello");
            out.print("{\"status\":\"error\", \"message\":\"path error occurred.\"}");
            return;
        }
    }
}
