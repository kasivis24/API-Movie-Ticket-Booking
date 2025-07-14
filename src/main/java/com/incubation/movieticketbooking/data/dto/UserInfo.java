package com.incubation.movieticketbooking.data.dto;

public class UserInfo {

    private String userUid;
    private String name;
    private String email;
    private String password;

    public UserInfo(String userUid, String name, String email, String password) {
        this.userUid = userUid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
