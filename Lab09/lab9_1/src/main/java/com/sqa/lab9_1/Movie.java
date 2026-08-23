package com.sqa.lab9_1;

public class Movie {

    private String title;
    private String cinemaType;
    private String location;
    private String date;

    public Movie(String title, String cinemaType, String location, String date) {
        this.title = title;
        this.cinemaType = cinemaType;
        this.location = location;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getCinemaType() {
        return cinemaType;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}