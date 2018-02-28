package io.github.akndmr.popvieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.github.akndmr.popvieapp.model.Movie;
import io.github.akndmr.popvieapp.rest.MovieService;
import io.github.akndmr.popvieapp.rest.RestClient;
import io.github.akndmr.popvieapp.utils.ConstantsUtil;
import io.github.akndmr.popvieapp.utils.ConvertUtil;
import io.github.akndmr.popvieapp.utils.DialogUtil;
import io.github.akndmr.popvieapp.utils.DisplayUtil;
import io.github.akndmr.popvieapp.utils.NetworkUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private final static String MOVIE_SAVED_INSTANCE_STATE_KEY = "movie_callback";
    private MovieService mService;
    private Movie movie;
    private TextView mTextViewMovieName, mTextViewDescription, mTextViewGenres,
            mTextViewRating, mTextViewPopularity, mTextViewRuntime,
            mTextViewMovieName2, mTextViewRuntime2,
            mTextViewGenres2, mTextViewRating2, mTextViewPopularity2,
            mTextViewRatingCount2, mTextViewTagline2, mTextViewOverview2,
            mTextViewReleaseDate2, mTextViewOfficialSite2, mTextViewBudget2,
            mTextViewRevenue2, mTextViewCountries2, mTextViewLanguages2,
            mTextViewCompanies2;
    private ImageView mImageViewPoster, mImageViewPopularityIcon, mImageViewRatingIcon, mImageViewRuntimIcon;
    private RatingBar mRatingBarRating2;
    private ProgressBar mProgressBarPopularity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        DisplayUtil.setCustomActionBarTitle(this, getSupportActionBar());
        bindViews();
        hideIcons();
        mService = new RestClient().mMovieService;
        String dialogNoInternet = this.getResources().getString(ConstantsUtil.DIALOG_NO_INTERNET);

        Intent movieIntent = getIntent();

        // POPULAR, TOP_RATED, NOW_PLAYING and UPCOMING responses does not include
        // all required data for this detail screen to be shown.
        // So, for now, we just get the selected movieId and make another request
        // for that movie to get all needed details.

        // Get selected movie's id
        int movieId = movieIntent.getIntExtra(ConstantsUtil.MOVIE_ID_EXTRA, 0);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(MOVIE_SAVED_INSTANCE_STATE_KEY);
            setValuesOfViews(movie);
        } else {
            // Check if there is any connection
            if (NetworkUtil.isConnected(this))
                fetchMovieByID(movieId);
            else
                DialogUtil.showDialogWith(MovieDetailsActivity.this, ConstantsUtil.POPVIE_IS_SAD, dialogNoInternet);
        }

    }

    // Fetch a single movie by id
    private void fetchMovieByID(int movieId) {
        final Call<Movie> call = mService.getMovieWithId(movieId, BuildConfig.TMDB_API_KEY);

        String request = call.request().toString();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie = response.body();
                setValuesOfViews(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showMsg(t.getMessage());
            }
        });
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // To keep onCreate() clean, all findViewById()'s called here.
    // On second stage, ViewBinding will be used.
    private void bindViews() {
        mTextViewMovieName = findViewById(R.id.tv_details_movie_name);
        mTextViewDescription = findViewById(R.id.tv_details_description);
        mTextViewGenres = findViewById(R.id.tv_details_genres);
        mTextViewPopularity = findViewById(R.id.tv_popularity);
        mTextViewRating = findViewById(R.id.tv_rating);
        mTextViewRuntime = findViewById(R.id.tv_runtime);
        mImageViewPoster = findViewById(R.id.iv_movie_poster);
        mImageViewPopularityIcon = findViewById(R.id.ic_rating);
        mImageViewRatingIcon = findViewById(R.id.ic_popularity);
        mImageViewRuntimIcon = findViewById(R.id.ic_runtime);

        mTextViewMovieName2 = findViewById(R.id.tv_md_title2);
        mTextViewRuntime2 = findViewById(R.id.tv_md_runtime);
        mTextViewGenres2 = findViewById(R.id.tv_md_genres);
        mTextViewRating2 = findViewById(R.id.tv_md_rating);
        mTextViewPopularity2 = findViewById(R.id.tv_md_popularity);
        mTextViewRatingCount2 = findViewById(R.id.tv_md_rating_count);
        mTextViewTagline2 = findViewById(R.id.tv_md_tagline);
        mTextViewOverview2 = findViewById(R.id.tv_md_overview);
        mRatingBarRating2 = findViewById(R.id.rb_md_ratingbar);
        mProgressBarPopularity2 = findViewById(R.id.pb_md_progressbar);


        mTextViewReleaseDate2 = findViewById(R.id.tv_md_release_date_full);
        mTextViewOfficialSite2 = findViewById(R.id.tv_md_official_site);
        mTextViewBudget2 = findViewById(R.id.tv_md_budget);
        mTextViewRevenue2 = findViewById(R.id.tv_md_revenue);
        mTextViewCountries2 = findViewById(R.id.tv_md_countries2);
        mTextViewLanguages2 = findViewById(R.id.tv_md_languages);
        mTextViewCompanies2 = findViewById(R.id.tv_md_companies);
    }

    // Hides imageviews of icons onCreate()
    private void hideIcons() {
        mImageViewRuntimIcon.setVisibility(View.GONE);
        mImageViewRatingIcon.setVisibility(View.GONE);
        mImageViewPopularityIcon.setVisibility(View.GONE);
    }

    // Shows hiden imageviews of icons
    private void showIcons() {
        mImageViewRuntimIcon.setVisibility(View.VISIBLE);
        mImageViewRatingIcon.setVisibility(View.VISIBLE);
        mImageViewPopularityIcon.setVisibility(View.VISIBLE);
    }

    //Build poster path to have a complete URL to image
    private String posterPathBuilder(String path) {
        Uri builtUri = Uri.parse(ConstantsUtil.POSTER_BASE_URL).buildUpon()
                .appendPath(ConstantsUtil.POSTER_SIZE_V_342)
                .appendEncodedPath(path)
                .build();

        return builtUri.toString();
    }

    // Loads imageView with the url given
    private void loadPoster(String urlToPoster) {
        Picasso.with(this).load(urlToPoster).placeholder(R.drawable.poster_placeholder).into(mImageViewPoster);
    }

    // Extract year from date format
    // Reference: https://stackoverflow.com/a/4216767
    private String getYearFromDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateFormated = format.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormated);
        int year = cal.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    // Website link click event
    public void visitOriginalWebsite(View v) {
        openUrl(mTextViewOfficialSite2.getText().toString());
    }

    // Opens clicked url
    private void openUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        this.startActivity(intent);
    }

    private String convertToMoneyString(String money) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(Double.parseDouble(money));
    }

    // Sets values of views from given Movie instance
    private void setValuesOfViews(Movie movie) {

        mTextViewMovieName.setText(movie.getTitle());
        mTextViewDescription.setText(movie.getOverview());

        String genres = ConvertUtil.listToString(movie.getGenres());
        mTextViewGenres.setText(genres);
        String popularity = String.format(Locale.US, "%1$.1f", movie.getPopularity());
        mTextViewPopularity.setText(popularity);
        mTextViewRating.setText(String.valueOf(movie.getVoteAverage()));
        mTextViewRuntime.setText(String.valueOf(movie.getRuntime()));
        String posterPath = posterPathBuilder(movie.getPosterPath());
        loadPoster(posterPath);

        showIcons();
        String year = "";
        try {
            year = getYearFromDate(movie.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String movieNameWithYearh = movie.getTitle() + " (" + year + ")";
        mTextViewMovieName2.setText(movieNameWithYearh);
        mTextViewGenres2.setText(genres);
        String runtime = String.valueOf(movie.getRuntime()) + " mins";
        mTextViewRuntime2.setText(runtime);
        String tagline = "“" + movie.getTagline() + "”";
        String tagline2 = tagline.equals("“”") ? "“---”" : tagline;
        mTextViewTagline2.setText(tagline2);
        mTextViewOverview2.setText(movie.getOverview());
        String rating = String.valueOf(movie.getVoteAverage());
        mTextViewRating2.setText(rating);
        String votes = getResources().getString(R.string.votes);
        String votesWithValue = String.valueOf(movie.getVoteCount()) + " " + votes;
        mTextViewRatingCount2.setText(votesWithValue);
        mTextViewPopularity2.setText(popularity);
        Double popularityDouble = movie.getPopularity();
        mRatingBarRating2.setRating(Float.parseFloat(rating));
        mProgressBarPopularity2.setProgress(popularityDouble.intValue());
        mTextViewReleaseDate2.setText(movie.getReleaseDate());
        String officialSite = movie.getHomepage().equals("") ? "---" : movie.getHomepage();
        mTextViewOfficialSite2.setText(officialSite);
        String budget = convertToMoneyString(String.valueOf(movie.getBudget()));
        mTextViewBudget2.setText(budget);
        String revenue = convertToMoneyString(String.valueOf(movie.getRevenue()));
        mTextViewRevenue2.setText(revenue);
        String countries = ConvertUtil.listToString(movie.getProductionCountries());
        String countries2 = countries.equals("") ? "---" : countries;
        mTextViewCountries2.setText(countries2);
        String languages = ConvertUtil.listToString(movie.getSpokenLanguages());
        String languages2 = languages.equals("") ? "---" : languages;
        mTextViewLanguages2.setText(languages2);
        String companies = ConvertUtil.listToString(movie.getProductionCompanies());
        String companies2 = companies.equals("") ? "---" : companies;
        mTextViewCompanies2.setText(companies2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE_SAVED_INSTANCE_STATE_KEY, movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAddInFavList:
                DialogUtil.showDialogWith(MovieDetailsActivity.this, ConstantsUtil.POPVIE_IS_HAPPY, "This feature will be added in next version. Stay tuned!");
                return true;
            case R.id.menuItemAddInWatchList:
                DialogUtil.showDialogWith(MovieDetailsActivity.this, ConstantsUtil.POPVIE_IS_HAPPY, "This feature will be added in next version. Stay tuned!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
