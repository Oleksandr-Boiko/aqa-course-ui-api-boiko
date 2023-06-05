package qa.projects;

import clients.IMDBClient;
import models.Movies;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class IMDBTests {
    private IMDBClient imdbClient = new IMDBClient();
    private String apiKey = "k_83pv5z04";

    @Test
    public void findSomeMovie() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "inception 2010").execute().body();
        System.out.println(movies);
        Assert.assertTrue(movies.moviesValue.get(0).title.equals("Inception"), "Title not equals");
        Assert.assertTrue(movies.moviesValue.get(0).description.contains("2010"), "Description not contains");
    }

    @Test
    public void sendEmptyExpression() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "").execute().body();
        Assert.assertTrue(movies.moviesValue.isEmpty(), "Results not empty");
        Assert.assertTrue(movies.errorMessage.contains("Invalid request."), "Error message not contains");
    }
}
