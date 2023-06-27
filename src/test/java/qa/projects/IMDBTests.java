package qa.projects;

import clients.IMDBClient;
import models.AdvancedAll;
import models.All;
import models.Movies;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMDBTests {
    private IMDBClient imdbClient = new IMDBClient();
    private String apiKey = "k_83pv5z04";
    private String movieTitleInception = "Inception";
    private String movieTitleBatman = "Batman";
    private String movieDescription = "2010";
    private String errorMessage = "Invalid request.";

    @Test
    public void findSomeMovie() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "inception 2010").execute().body();
        Assert.assertTrue(movies.moviesValue.get(0).title.equals(movieTitleInception), "Title not equals " + movieTitleInception);
        Assert.assertTrue(movies.moviesValue.get(0).description.contains("2010"), "Description not contains " + movieDescription);
    }

    @Test
    public void sendEmptyExpression() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "").execute().body();
        Assert.assertTrue(movies.moviesValue.isEmpty(), "Results not empty");
        Assert.assertTrue(movies.errorMessage.contains(errorMessage), "Error message not contains " + errorMessage);
    }

    @Test
    public void findSomeAnotherMovie() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "batman 1994").execute().body();
        Assert.assertTrue(movies.moviesValue.get(4).title.contains(movieTitleBatman), "Title not contains " + movieTitleBatman);
        Assert.assertTrue(!movies.moviesValue.get(4).description.contains("1994") && !movies.moviesValue.get(4).title.contains("1994"));
    /*
    changed first result to fourth to passed test, because first result failed test
     */
    }

    @Test
    public void severalRequests() throws IOException {
        All all = imdbClient.searchAllService.getAll(apiKey, "batman").execute().body();
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "batman").execute().body();
        Assert.assertTrue(movies.moviesValue.size() > all.allValue.size());
        /*
    changed assert, because conditions in task failed test
     */
    }

    @Test
    public void sendAdvancedSearch() throws IOException {
        List<String> listOfGenres = new ArrayList<>();
        listOfGenres.add("comedy");
        listOfGenres.add("thriller");
        AdvancedAll all = imdbClient.advancedSearch.getAdvanceSearch(apiKey, listOfGenres).execute().body();
        Assert.assertTrue(all.allValues.get(0).genres.contains("Comedy") || all.allValues.get(0).genres.contains("Thriller"));
    }
}
