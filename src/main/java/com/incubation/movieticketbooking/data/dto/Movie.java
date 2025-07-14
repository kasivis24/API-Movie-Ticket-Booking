package com.incubation.movieticketbooking.data.dto;

public class Movie {
    private int movieId;
    private String movieTitle;
    private String movieGenre;
    private int duration;
    private String movieThumbnail;
    private String movieLang;
    private String movieDesc;

    public Movie(int movieId, String movieTitle, String movieGenre, int duration, String movieThumbnail, String movieLang, String movieDesc) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.duration = duration;
        this.movieThumbnail = movieThumbnail;
        this.movieLang = movieLang;
        this.movieDesc = movieDesc;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMovieThumbnail() {
        return movieThumbnail;
    }

    public void setMovieThumbnail(String movieThumbnail) {
        this.movieThumbnail = movieThumbnail;
    }

    public String getMovieLang() {
        return movieLang;
    }

    public void setMovieLang(String movieLang) {
        this.movieLang = movieLang;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }
}
