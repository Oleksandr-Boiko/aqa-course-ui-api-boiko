package clients;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.AdvancedSearch;
import services.SearchAll;
import services.SearchMovies;

public class IMDBClient {

    public SearchMovies searchMoviesService;
    public SearchAll searchAllService;
    public AdvancedSearch advancedSearch;

    public IMDBClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        searchMoviesService = retrofit.create(SearchMovies.class);
        searchAllService = retrofit.create(SearchAll.class);
        advancedSearch = retrofit.create(AdvancedSearch.class);
    }
}
