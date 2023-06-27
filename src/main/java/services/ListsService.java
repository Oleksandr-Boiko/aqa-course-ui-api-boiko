package services;

import models.Lists;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;


public interface ListsService {

    @GET("/1/boards/{id}/lists")
    Call<List<Lists>> getLists(@Path("id") String id, @Query("key") String apiKey, @Query("token") String apiToken);

}
