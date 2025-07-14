package com.incubation.movieticketbooking.data.dto;

public class Theatre {
    private int theatreId;
    private String theatreName;
    private String theatreLocation;

    public Theatre(int theatreId, String theatreName, String theatreLocation) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getTheatreLocation() {
        return theatreLocation;
    }

    public void setTheatreLocation(String theatreLocation) {
        this.theatreLocation = theatreLocation;
    }
}
