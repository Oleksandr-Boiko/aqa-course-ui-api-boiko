package services;

import models.All;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchAll {

    @GET("/API/SearchAll/{apiKey}/{expression}")
    Call<All> getAll(@Path("apiKey") String apiKey, @Path("expression") String expression);
}
