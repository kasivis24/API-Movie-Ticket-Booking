package com.incubation.movieticketbooking.data.dto;

public class Show {
    private int showId;
    private int movieId;
    private int theatreId;
    private String dateTime;
    private int basePrice;
    private int availableSeat;
    private String title;
    private String name;
    private String location;

    public Show(int showId, int movieId, int theatreId, String dateTime, int basePrice, int availableSeat, String title, String name, String location) {
        this.showId = showId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.dateTime = dateTime;
        this.basePrice = basePrice;
        this.availableSeat = availableSeat;
        this.title = title;
        this.name = name;
        this.location = location;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
