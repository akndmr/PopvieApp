package io.github.akndmr.popvieapp.utils;

import io.github.akndmr.popvieapp.R;

/**
 * Created by AKIN on 25.02.2018.
 */

public class ConstantsUtil {

    public static final String SORT_BY_POPULARITY = "popular";
    public static final String SORT_BY_RATING = "top_rated";
    public static final String SORT_BY_NOWPLAYING = "now_playing";
    public static final String SORT_BY_UPCOMING = "upcoming";

    public final static String POSTER_BASE_URL = "https://image.tmdb.org/t/p";
    public final static String POSTER_SIZE_V_185 = "w185";
    public final static String POSTER_SIZE_V_342 = "w342";

    public final static String MOVIE_ID_EXTRA = "movie_id_extra";
    public final static String MOVIE_PARCELABLE_KEY = "movie_parcelable_key";

    public final static int POPVIE_IS_SAD = R.drawable.popvie_sad;
    public final static int POPVIE_IS_HAPPY = R.drawable.popvie_happy;

    public final static int DIALOG_NO_INTERNET = R.string.dialog_no_internet_connection;
    public final static int DIALOG_SUCCESSFUL_API_RESPONSE = R.string.dialog_succesful_api_response;
    public final static int DIALOG_LOGIN_WELCOME = R.string.dialog_succesful_login;
}
