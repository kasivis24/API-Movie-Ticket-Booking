package com.incubation.movieticketbooking.utils;

import jakarta.servlet.http.Cookie;

public class Utils {

    public static boolean isAuthenticatedPerson(Cookie[] cookies){
        System.out.println("coming web filter ");
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("userToken".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getAuthToken(Cookie[] cookies){
        String authToken = "";
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("userToken".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                    return authToken;
                }
            }
        }
        return authToken;
    }
}
