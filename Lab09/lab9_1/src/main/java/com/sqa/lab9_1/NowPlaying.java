package com.sqa.lab9_1;

import java.util.ArrayList;
import java.util.List;

public class NowPlaying {

    private MovieService movieService;

    public NowPlaying(MovieService movieService) {
        this.movieService = movieService;
    }

    public String getMoviesByCinemaType(String location, String date, String cinemaType) {
        List<Movie> playlist = movieService.getPlaylist(location, date);
        List<String> titles = new ArrayList<String>();

        for (Movie movie : playlist) {
            if (movie.getCinemaType().equalsIgnoreCase(cinemaType)) {
                titles.add(movie.getTitle());
            }
        }
        return String.join(", ", titles);
    }
}