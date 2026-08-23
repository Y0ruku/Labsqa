package com.sqa.lab9_1;

import java.util.List;

public interface MovieService {
    List<Movie> getPlaylist(String location, String date);
}