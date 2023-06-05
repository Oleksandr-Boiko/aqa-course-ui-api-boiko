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
    public void test1() throws IOException {
        Movies movies = imdbClient.searchMoviesService.getMovie(apiKey, "inception 2010").execute().body();
        System.out.println(movies);
        Assert.assertTrue(movies.moviesValue.get(0).title.equals("Inception"), "Title not equals");
        Assert.assertTrue(movies.moviesValue.get(0).description.contains("2010"), "Description not contains");
    }
}
