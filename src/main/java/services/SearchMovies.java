package services;

import models.Movies;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchMovies {

    @GET("/API/SearchMovie/{apiKey}/{expression}")
    Call<Movies> getMovie(@Path("apiKey") String apiKey, @Path("expression") String expression);
}
