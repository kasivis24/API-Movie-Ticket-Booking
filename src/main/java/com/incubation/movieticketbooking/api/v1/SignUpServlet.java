package com.incubation.movieticketbooking.api.v1;


import com.google.gson.Gson;
import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.LoginInfo;
import com.incubation.movieticketbooking.data.dto.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/api/v1/signup")
public class SignUpServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public SignUpServlet() throws SQLException {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("get caled");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();

//        HttpSession session = req.getSession(false);
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();

        UserInfo data = new Gson().fromJson(json, UserInfo.class);

        PrintWriter out = resp.getWriter();

        String name = data.getName();
        String email = data.getEmail();
        String password = data.getPassword();
        String userUid = UUID.randomUUID().toString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println(name);
        System.out.println(email);

        try {
            if ((name != null && email != null && password != null && !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) && !email.contains("admin")){
                appDb.addUserData(new UserInfo(userUid,name,email,password));
                Cookie authCookie = new Cookie("userToken",userUid);
                authCookie.setHttpOnly(true);
                authCookie.setMaxAge(60 * 60);
                authCookie.setPath("/");
                resp.addCookie(authCookie);
//                session.setAttribute("userToken",userUid);
                System.out.println(name);
                System.out.println(email);
                System.out.println(userUid);
                out.print("{\"status\":\"success\",\"message\":\"User SignUp successfully\", \"userUid\": \"" + userUid + "\"}");
            } else if ((name != null && email != null && password != null && !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) && email.contains("admin")){
                String adminUid = UUID.randomUUID().toString();
                if (appDb.addAdminData(adminUid,name,email,password)){
                    System.out.println("Admin login success");
                    Cookie authCookie = new Cookie("adminToken",adminUid);
                    authCookie.setHttpOnly(true);
                    authCookie.setMaxAge(60 * 60);
                    authCookie.setPath("/");
                    resp.addCookie(authCookie);
                    out.print("{\"status\":\"success\",\"message\":\"Admin SignUp successfully\", \"adminUid\": \"" + adminUid + "\", \"userType\": \"Admin\"}");
                }else {
                    out.print("{\"status\":\"error\",\"message\":\"Email already exists\"}");
                }
            }else {
                out.print("{\"status\":\"error\",\"message\":\"Email already exists\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
