package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/v1/movies")
public class MoviesServlet extends HttpServlet {

    private AppDb appDb = AppDb.getInstance();

    public MoviesServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        List<Movie> allMoviesList = null;
        try {
            allMoviesList = appDb.getAllMovies();
            String json = new Gson().toJson(allMoviesList);
            out.print(json);
        } catch (SQLException e) {
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            throw new RuntimeException(e);
        }

    }
}
