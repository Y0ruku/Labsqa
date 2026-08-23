package com.sqa.lab9_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NowPlayingTest {

    @Test
    void shouldReturnOnlyVIPMovies_whenFilteredByVIPCinemaType() {
        MovieService stubMovieService = new MovieServiceStub();
        NowPlaying nowPlaying = new NowPlaying(stubMovieService);

        String result = nowPlaying.getMoviesByCinemaType("Central Plaza Khonkaen", "2026-09-15", "VIP");

        assertEquals("Your Name, The Garden of Words", result);
    }

    @Test
    void shouldReturnAllIMAXMovies_whenFilteredByIMAXWithLaserCinemaType() {
        MovieService stubMovieService = new MovieServiceStub();
        NowPlaying nowPlaying = new NowPlaying(stubMovieService);

        String result = nowPlaying.getMoviesByCinemaType("Central Plaza Khonkaen", "2026-09-15", "IMAX with Laser");

        assertEquals("The Odyssey, Spider-Man: Brand New Day, The End of Oak Street", result);
    }

    @Test
    void shouldReturnEmptyString_whenNoMovieMatchesCinemaType() {
        MovieService stubMovieService = new MovieServiceStub();
        NowPlaying nowPlaying = new NowPlaying(stubMovieService);

        String result = nowPlaying.getMoviesByCinemaType("Central Plaza Khonkaen", "2026-09-15", "4DX");

        assertEquals("", result);
    }


}
