package clients;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.BoardService;
import services.CardService;
import services.ListsService;
import services.OrganizationService;

public class TrelloClient {

    public BoardService boardService;
    public OrganizationService organizationService;
    public ListsService listsService;
    public CardService cardService;

    public TrelloClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trello.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        boardService = retrofit.create(BoardService.class);
        organizationService = retrofit.create(OrganizationService.class);
        listsService = retrofit.create(ListsService.class);
        cardService = retrofit.create(CardService.class);
    }
}
