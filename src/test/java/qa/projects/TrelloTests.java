package qa.projects;

import clients.TrelloClient;
import models.Board;
import models.Card;
import models.Lists;
import models.Organization;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class TrelloTests {

    private TrelloClient trelloClient = new TrelloClient();
    private String apiKey = "d3b603fdaa559811b46ccb23a48b77b6";
    private String apiToken = "140f3dd7e297b0a11906e71f55a835296304f59f8601e81ad419fa14de959176";
    private String orgName = "test";
    private String boardName = "asdasd";
    private String cardName = "myNewCard";
    private String boardID;
    Organization org;

    @BeforeClass
    public void createNewOrganizationTest() throws IOException {
        org = trelloClient.organizationService.createOrganization(orgName, apiKey, apiToken).execute().body();
    }

    @Test
    public void createNewBoard() throws IOException {
        Call<Board> call = trelloClient.boardService.createBoard(boardName, org.id, apiKey, apiToken);
        Response<Board> response = call.execute();
        boardID = response.body().id;
        Assert.assertTrue(response.isSuccessful());
        Assert.assertTrue(response.body().name.equals(boardName));
    }

    @Test(dependsOnMethods = {"createNewBoard"})
    public void addCardToList() throws IOException {
        List<Lists> list = trelloClient.listsService.getLists(boardID, apiKey, apiToken).execute().body();
        String idOfList = list.get(0).id;
        Call<Card> call = trelloClient.cardService.createCard(cardName, idOfList, apiKey, apiToken);
        Response<Card> response = call.execute();
        Assert.assertTrue(response.isSuccessful());
        String idOfCard = response.body().id;
        Assert.assertTrue(response.body().name.equals(cardName));
        Card card = trelloClient.cardService.getCard(boardID, idOfCard, apiKey, apiToken).execute().body();
        Assert.assertTrue(card.name.equals(cardName));

    }

    @Test(dependsOnMethods = {"createNewBoard"})
    public void deleteBoard() throws IOException {
        Boolean response = trelloClient.boardService.deleteBoard(boardID, apiKey, apiToken).execute().isSuccessful();
        Assert.assertTrue(response);
        String message = trelloClient.boardService.getBoard(boardID, apiKey, apiToken).execute().errorBody().string();
        Assert.assertTrue(message.equals("The requested resource was not found."));
    }
}
