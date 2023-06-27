package services;

import models.Board;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface BoardService {

    @GET("/1/boards/{id}")
    Call<ResponseBody> getBoard(@Path("id") String id, @Query("key") String apiKey, @Query("token") String apiToken);

    @POST("/1/boards/")
    Call<Board> createBoard(@Query("name") String name, @Query("idOrganization") String orgID,
                            @Query("key") String apiKey, @Query("token") String apiToken);

    @DELETE("/1/boards/{id}")
    Call<ResponseBody> deleteBoard(@Path("id") String id, @Query("key") String apiKey, @Query("token") String apiToken);
}
