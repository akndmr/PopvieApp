package io.github.akndmr.popvieapp.rest;

import io.github.akndmr.popvieapp.model.Movie;
import io.github.akndmr.popvieapp.model.MoviesWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AKIN on 25.02.2018.
 */

public interface MovieService {

    //Get a movie
    @GET("movie/{id}")
    Call<Movie> getMovieWithId(@Path("id") int movieId, @Query("api_key") String api_key);

    //Get popular, top rated, now playing and upcoming movies
    @GET("movie/{sortBy}")
    Call<MoviesWrapper> getMoviesBy(@Path("sortBy") String sortBy, @Query("api_key") String api_key);

}
