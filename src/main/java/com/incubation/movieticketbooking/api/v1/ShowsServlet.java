package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Show;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/v1/shows")
public class ShowsServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public ShowsServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");

        String movieIdParam = req.getParameter("movieId");
        String theatreIdParam = req.getParameter("theatreId");


        if (movieIdParam == null || theatreIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"movieId and theatreId are required\"}");
            return;
        }

        int movieId = Integer.parseInt(movieIdParam);
        int theatreId = Integer.parseInt(theatreIdParam);
        List<Show> showList = new ArrayList<>();

        try {
            showList = appDb.getShowsByTheatre(movieId,theatreId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sdsidsdm");
        String response = new Gson().toJson(showList);
        System.out.println("sdsidsdm");
        out.print(response);
    }

}
