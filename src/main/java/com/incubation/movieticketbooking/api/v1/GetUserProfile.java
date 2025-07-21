package com.incubation.movieticketbooking.api.v1;

import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.UserProfileInfo;
import com.incubation.movieticketbooking.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/v1/user-profile")
public class GetUserProfile extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();


    public GetUserProfile() throws SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("user profile date ");
        String userUid = Utils.getAuthToken(req.getCookies());
        PrintWriter out = resp.getWriter();

        if (!userUid.isEmpty() && userUid != null){
            System.out.println("Suer ");
            try {
                System.out.println("User profile");
                UserProfileInfo userProfileInfo = appDb.getUserProfile(userUid);
                String json = new Gson().toJson(userProfileInfo);
                out.print(json);
            } catch (SQLException e) {
                System.out.println("User error ");
                out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
                throw new RuntimeException(e);
            }
        }

    }
}
