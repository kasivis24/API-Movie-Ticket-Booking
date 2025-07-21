package com.incubation.movieticketbooking.data.dto;

public class BookingHistory {
    private String movieName;
    private String theatreName;
    private int numberOfSeats;
    private int amount;

    public BookingHistory(String movieName, String theatreName, int numberOfSeats, int amount) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.numberOfSeats = numberOfSeats;
        this.amount = amount;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
