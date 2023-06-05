package clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.SearchMovies;

public class IMDBClient {

    public SearchMovies searchMoviesService;

    public IMDBClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchMoviesService = retrofit.create(SearchMovies.class);
    }
}
