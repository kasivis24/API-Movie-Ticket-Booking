package com.incubation.movieticketbooking.data.dto;

public class UserProfileInfo {
    private String name;
    private String gmail;

    public UserProfileInfo(String name, String gmail) {
        this.name = name;
        this.gmail = gmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
