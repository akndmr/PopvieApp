package io.github.akndmr.popvieapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.akndmr.popvieapp.adapter.MoviesAdapter;
import io.github.akndmr.popvieapp.model.MoviesWrapper;
import io.github.akndmr.popvieapp.rest.MovieService;
import io.github.akndmr.popvieapp.rest.RestClient;
import io.github.akndmr.popvieapp.utils.ConstantsUtil;
import io.github.akndmr.popvieapp.utils.DialogUtil;
import io.github.akndmr.popvieapp.utils.DisplayUtil;
import io.github.akndmr.popvieapp.utils.NetworkUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private static final String TAG = MoviesActivity.class.getSimpleName();
    private static final String SAVED_INSTANCE_STATE_KEY = "callback";
    private static final String FINISH_ACTIVITY = "finish";
    int mNoConectionStringId = 0;
    int mPopviewCharacterSadId = 0;
    private MovieService mService;
    private MoviesAdapter mMoviesAdapter;
    private MoviesWrapper mMoviesWrapperState;
    private RecyclerView mRecyclerView;
    // Buttom navigation click listener
    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottomNavItemSortByNowPlaying:
                    checkConnectionFetchMovie(ConstantsUtil.SORT_BY_NOWPLAYING);
                    return true;
                case R.id.bottomNavItemSortByPopularity:
                    checkConnectionFetchMovie(ConstantsUtil.SORT_BY_POPULARITY);
                    return true;
                case R.id.bottomNavItemSortByRating:
                    checkConnectionFetchMovie(ConstantsUtil.SORT_BY_RATING);
                    return true;
                case R.id.bottomNavSortByUpcoming:
                    checkConnectionFetchMovie(ConstantsUtil.SORT_BY_UPCOMING);
                    return true;
            }
            return false;
        }
    };
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_movies);
        mService = new RestClient().mMovieService;
        mRecyclerView = findViewById(R.id.rv_movies);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavItemSelectListener);

        DisplayUtil.setCustomActionBarTitle(this, getSupportActionBar());

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SAVED_INSTANCE_STATE_KEY)) {
                mMoviesWrapperState = savedInstanceState.getParcelable(SAVED_INSTANCE_STATE_KEY);
                populateMoviesAdapter(mMoviesWrapperState);
            }
        } else {
            // Checks if there is connection, if so fetches movie from API
            checkConnectionFetchMovie(ConstantsUtil.SORT_BY_NOWPLAYING);
        }
    }

    // Fetch movies - sort by popular, top_rated, now_playing, upcoming
    private void fethMovieBySortOrder(String sortBy) {
        Call<MoviesWrapper> call = mService.getMoviesBy(sortBy, BuildConfig.TMDB_API_KEY);
        String request = call.request().toString();
        DialogUtil.showDialogWith(MoviesActivity.this, ConstantsUtil.POPVIE_IS_HAPPY, getString(ConstantsUtil.DIALOG_SUCCESSFUL_API_RESPONSE));
        call.enqueue(new Callback<MoviesWrapper>() {
            @Override
            public void onResponse(Call<MoviesWrapper> call, Response<MoviesWrapper> response) {
                DialogUtil.closeDialog();
                populateMoviesAdapter(response.body());
                mMoviesWrapperState = response.body();
            }

            @Override
            public void onFailure(Call<MoviesWrapper> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void checkConnectionFetchMovie(String sortBy) {
        // Check if there is any connection
        if (NetworkUtil.isConnected(getApplicationContext()))
            fethMovieBySortOrder(sortBy);
        else
            DialogUtil.showDialogWith(MoviesActivity.this, ConstantsUtil.POPVIE_IS_SAD, getResources().getString(ConstantsUtil.DIALOG_NO_INTERNET));
    }

    private void populateMoviesAdapter(MoviesWrapper moviesWrapper) {
        mRecyclerView.setVisibility(View.GONE);
        mMoviesAdapter = new MoviesAdapter(this, moviesWrapper);
        int gridItemWidth = extractSizeFromPoster(ConstantsUtil.POSTER_SIZE_V_342);

        // calculateNoOfColumns gets poster ImageView width(100dp)
        // as second parameter
        // See poster ImageView in `single_movie.xml` layout
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(this, DisplayUtil.calculateNoOfColumns(this, 100));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMoviesAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    // Removes the 'w' char of poster sizes (eg. w185 to 185)
    private int extractSizeFromPoster(String sizeString) {
        StringBuilder sb = new StringBuilder(sizeString);
        sb.deleteCharAt(0);
        return Integer.parseInt(sb.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemSortByNowPlaying:
                //checkConnectionFetchMovie(ConstantsUtil.SORT_BY_NOWPLAYING);
                bottomNavigationView.setSelectedItemId(R.id.bottomNavItemSortByNowPlaying);
                return true;
            case R.id.menuItemSortByPopularity:
                //checkConnectionFetchMovie(ConstantsUtil.SORT_BY_POPULARITY);
                bottomNavigationView.setSelectedItemId(R.id.bottomNavItemSortByPopularity);
                return true;
            case R.id.menuItemSortByRating:
                //checkConnectionFetchMovie(ConstantsUtil.SORT_BY_RATING);
                bottomNavigationView.setSelectedItemId(R.id.bottomNavItemSortByRating);
                return true;
            case R.id.menuItemSortByUpcoming:
                //checkConnectionFetchMovie(ConstantsUtil.SORT_BY_UPCOMING);
                bottomNavigationView.setSelectedItemId(R.id.bottomNavSortByUpcoming);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_INSTANCE_STATE_KEY, mMoviesWrapperState);
    }
}
