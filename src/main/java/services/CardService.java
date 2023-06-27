package services;

import models.Card;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface CardService {

    @GET("/1/boards/{id}/cards/{idCard}")
    Call<Card> getCard(@Path("id") String boardID, @Path("idCard") String cardID,
                       @Query("key") String apiKey, @Query("token") String apiToken);

    @POST("/1/cards")
    Call<Card> createCard(@Query("name") String name, @Query("idList") String idList,
                          @Query("key") String apiKey, @Query("token") String apiToken);
}
