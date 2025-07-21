package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Theatre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/v1/all-theatre")
public class GetAllTheatreServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public GetAllTheatreServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        try {
            List<Theatre> theatreList = appDb.getAllTheatre();
            String json = new Gson().toJson(theatreList);
            out.print(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
