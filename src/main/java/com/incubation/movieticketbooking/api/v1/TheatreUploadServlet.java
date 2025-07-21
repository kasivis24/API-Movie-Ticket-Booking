package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Theatre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/v1/upload-theatre")
public class TheatreUploadServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public TheatreUploadServlet() throws SQLException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Theatre theatreData = gson.fromJson(reader, Theatre.class);
        PrintWriter out = resp.getWriter();


        try {
            if (appDb.uploadTheatre(theatreData)){
                out.print("{\"status\":\"success\",\"message\":\"Theatre uploaded\"}");
            }else {
                out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            }
        } catch (SQLException e) {
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            throw new RuntimeException(e);
        }

    }
}
