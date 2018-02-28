package io.github.akndmr.popvieapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AKIN on 25.02.2018.
 */

public class RestClient {

    private final static String BASE_URL = "https://api.themoviedb.org/3/";
    public final MovieService mMovieService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMovieService = retrofit.create(MovieService.class);
    }
}
