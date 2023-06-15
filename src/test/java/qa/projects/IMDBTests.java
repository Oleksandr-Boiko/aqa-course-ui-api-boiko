package qa.projects;

import clients.IMDBClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import models.AdvancedAll;
import models.All;
import models.Movies;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Owner("Boiko Oleksandr")
@Epic("IMDB")
public class IMDBTests {
    private IMDBClient imdbClient = new IMDBClient();
    private String apiKey = "k_83pv5z04";

    @Description("Check find movie functionality")
    @Test
    public void findSomeMovie() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "inception 2010").execute().body();
        Assert.assertTrue(movies.moviesValue.get(0).title.equals("Inception"), "Title not equals");
        Assert.assertTrue(movies.moviesValue.get(0).description.contains("2010"), "Description not contains");
    }

    @Description("Check empty expression")
    @Test
    public void sendEmptyExpression() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "").execute().body();
        Assert.assertTrue(movies.moviesValue.isEmpty(), "Results not empty");
        Assert.assertTrue(movies.errorMessage.contains("Invalid request."), "Error message not contains");
    }

    @Description("Check find movie functionality with another expression")
    @Test
    public void findSomeAnotherMovie() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "batman 1994").execute().body();
        Assert.assertTrue(movies.moviesValue.get(0).title.contains("Batman"), "Title not contains");
        Assert.assertTrue(!movies.moviesValue.get(0).description.contains("1994") && !movies.moviesValue.get(0).title.contains("1994"));
    }

    @Description("Check difference between responses")
    @Test
    public void severalRequests() throws IOException {
        All all = imdbClient.searchAllService.getAll(apiKey, "batman").execute().body();
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "batman").execute().body();
        Assert.assertTrue(movies.moviesValue.size() > all.allValue.size());
    }

    @Description("Check advanced search functionality")
    @Test
    public void sendAdvancedSearch() throws IOException {
        List<String> listOfGenres = new ArrayList<>();
        listOfGenres.add("comedy");
        listOfGenres.add("thriller");
        AdvancedAll all = imdbClient.advancedSearch.getAdvanceSearch(apiKey, listOfGenres).execute().body();
        Assert.assertTrue(all.allValues.get(0).genres.contains("Comedy") || all.allValues.get(0).genres.contains("Thriller"));
    }
}
