package com.incubation.movieticketbooking.data.dto;

import java.util.List;

public class MovieTheatreMapping {
    private Movie movie;
    private List<Theatre> theatreList;

    public MovieTheatreMapping(Movie movie, List<Theatre> theatreList) {
        this.movie = movie;
        this.theatreList = theatreList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Theatre> getTheatreList() {
        return theatreList;
    }

    public void setTheatreList(List<Theatre> theatreList) {
        this.theatreList = theatreList;
    }
}
