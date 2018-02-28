package io.github.akndmr.popvieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AKIN on 25.02.2018.
 */

public class MoviesWrapper implements Parcelable {

    public static final Parcelable.Creator<MoviesWrapper> CREATOR = new Parcelable.Creator<MoviesWrapper>() {
        @Override
        public MoviesWrapper createFromParcel(Parcel source) {
            return new MoviesWrapper(source);
        }

        @Override
        public MoviesWrapper[] newArray(int size) {
            return new MoviesWrapper[size];
        }
    };
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;

    public MoviesWrapper() {
    }

    protected MoviesWrapper(Parcel in) {
        this.page = in.readInt();
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
        this.movies = in.createTypedArrayList(Movie.CREATOR);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
        dest.writeTypedList(this.movies);
    }
}
