package com.incubation.movieticketbooking.data.dto;

public class Seat {
    private int seatId;
    private String seatNumber;
    private int isBooked;

    public Seat(int seatId, String seatNumber, int isBooked) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(int isBooked) {
        this.isBooked = isBooked;
    }
}
