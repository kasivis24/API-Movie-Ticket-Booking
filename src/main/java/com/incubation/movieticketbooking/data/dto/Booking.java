package com.incubation.movieticketbooking.data.dto;

import java.util.List;

public class Booking {
    private int movieId;
    private int theatreId;
    private int showId;
    private List<String> seatIds;

    public Booking(int movieId, int theatreId, int showId, List<String> seatIds) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showId = showId;
        this.seatIds = seatIds;
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

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }
}
