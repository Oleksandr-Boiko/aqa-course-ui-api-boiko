package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {

    @SerializedName("results")
    public List<Movie> moviesValue;

    public String errorMessage;

    @Override
    public String toString() {
        return "Movies{" +
                "moviesValue=" + moviesValue +
                '}';
    }
}
