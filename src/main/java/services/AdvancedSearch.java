package services;

import com.beust.jcommander.Parameters;
import models.AdvancedAll;
import models.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AdvancedSearch {

    @GET("/API/AdvancedSearch/{apiKey}/?parameters=values")
    Call<AdvancedAll> getAdvanceSearch(@Path("apiKey") String apiKey, @Query("genres") List<String> genres);
}
