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
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/api/v1/login")
public class LoginServlet extends HttpServlet {

    private final AppDb appDb = AppDb.getInstance();

    public LoginServlet() throws SQLException {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String json = sb.toString();

        LoginInfo data = new Gson().fromJson(json, LoginInfo.class);

        PrintWriter out = resp.getWriter();

        String email = data.getEmail();
        String password = data.getPassword();


//        HttpSession session = req.getSession(false);

        try {
            if (email != null && password != null && !email.isEmpty() && !password.isEmpty()){
                if (appDb.loginVerify(new LoginInfo(email,password))){
                    String userUid = appDb.getUserUid(email);
                    Cookie authCookie = new Cookie("userToken",userUid);
                    authCookie.setHttpOnly(true);
                    authCookie.setMaxAge(60 * 60);
                    authCookie.setPath("/");
                    resp.addCookie(authCookie);
//                    session.setAttribute("userToken",userUid);
                    out.print("{\"status\":\"success\",\"message\":\"User Login successfully\", \"userUid\": \"" + userUid + "\"}");
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
