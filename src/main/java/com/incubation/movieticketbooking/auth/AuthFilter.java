package com.incubation.movieticketbooking.auth;

import com.incubation.movieticketbooking.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/dashboard")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse)  servletResponse;

        System.out.println("Filtering");

        if (Utils.isAuthenticatedPerson(req.getCookies()) || Utils.isAuthenticatedAdmin(req.getCookies())){
            filterChain.doFilter(req,res);
        }else {
            res.sendRedirect( "/html/login.html");
        }

        System.out.println(req.getContextPath());
    }
}
