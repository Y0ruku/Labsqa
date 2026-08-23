package com.sqa.lab9_1;

import java.util.Arrays;
import java.util.List;

public class MovieServiceStub implements MovieService {

    @Override
    public List<Movie> getPlaylist(String location, String date) {
        return Arrays.asList(
            new Movie("The Odyssey", "IMAX with Laser", location, date),
            new Movie("Spider-Man: Brand New Day", "IMAX with Laser", location, date),
            new Movie("The End of Oak Street", "IMAX with Laser", location, date),
            new Movie("Your Name", "VIP", location, date),
            new Movie("The Garden of Words", "VIP", location, date),
            new Movie("2.5 Centimeters Per Second", "Standard", location, date)
        );
    }
}