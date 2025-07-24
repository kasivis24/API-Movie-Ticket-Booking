package com.incubation.movieticketbooking.data.dto;

public class SeatSelectInfo {
    private int showId;
    private int seatNumber;

    public SeatSelectInfo(int showId, int seatNumber) {
        this.showId = showId;
        this.seatNumber = seatNumber;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
