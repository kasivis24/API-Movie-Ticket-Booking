package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Show;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/v1/upload-show")
public class ShowUploadServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();


    public ShowUploadServlet() throws SQLException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        Show show = new Gson().fromJson(reader,Show.class);
        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");

        try {
            if (appDb.uploadShow(show)){
                out.print("{\"status\":\"success\",\"message\":\"Show uploaded\"}");
            }else {
                out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            }
        } catch (SQLException e) {
            out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            throw new RuntimeException(e);
        }

    }
}
