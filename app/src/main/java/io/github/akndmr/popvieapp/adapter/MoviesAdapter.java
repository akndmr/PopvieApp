package io.github.akndmr.popvieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.akndmr.popvieapp.MovieDetailsActivity;
import io.github.akndmr.popvieapp.R;
import io.github.akndmr.popvieapp.model.Movie;
import io.github.akndmr.popvieapp.model.MoviesWrapper;
import io.github.akndmr.popvieapp.utils.ConstantsUtil;

/**
 * Created by AKIN on 25.02.2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final Context mContext;
    private final MoviesWrapper mMoviesWrapper;

    public MoviesAdapter(Context context, MoviesWrapper moviesWrapper) {
        this.mContext = context;
        this.mMoviesWrapper = moviesWrapper;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final Movie movie = mMoviesWrapper.getMovies().get(position);


        holder.movieRating.setText(String.valueOf(movie.getVoteAverage()));
        String posterPath = posterPathBuilder(movie.getPosterPath());
        Picasso.with(mContext)
                .load(posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)
                .into(holder.moviePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                // intent.putExtra(ConstantsUtil.MOVIE_PARCELABLE_KEY, movie); FOR LATER USE
                intent.putExtra(ConstantsUtil.MOVIE_ID_EXTRA, movie.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMoviesWrapper.getMovies().size();
    }

    //Build poster path to have a complete URL to image
    private String posterPathBuilder(String path) {
        Uri builtUri = Uri.parse(ConstantsUtil.POSTER_BASE_URL).buildUpon()
                .appendPath(ConstantsUtil.POSTER_SIZE_V_185)
                .appendEncodedPath(path)
                .build();

        return builtUri.toString();
    }

    public interface MoviesOnClickListener {
        void onMovieClick(int position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        final TextView movieRating;
        final ImageView moviePoster;

        public MovieViewHolder(View v) {
            super(v);
            movieRating = v.findViewById(R.id.tv_single_rating);
            moviePoster = v.findViewById(R.id.iv_single_movie_poster);
        }
    }
}
