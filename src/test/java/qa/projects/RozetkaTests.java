package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class RozetkaTests {

    private String iphone = "iphone";
    private String iphone13 = "iphone 13";
    private String apple = "Apple";

    @BeforeClass
    public void openSite() {
        open(MainPage.baseURL);
    }

    @BeforeMethod
    public void returnHomePage() {
        open(MainPage.baseURL);
    }

    @Test
    public void test1() {
        MainPage.cartButton.click();
        CartPage.cartModalName.shouldBe(Condition.text("Корзина"));
        CartPage.cartEmptyState.shouldBe(Condition.visible);
        CartPage.closeModalButton.click();
        MainPage.search(iphone);
        MainPage.listOfGoods.shouldHave(CollectionCondition.size(30));
        MainPage.buyButton.get(0).hover().click();
        MainPage.cartBadgeIcon.shouldBe(Condition.text("1"));
        MainPage.cartButton.click();
        CartPage.cartEmptyState.shouldBe(Condition.hidden);
        CartPage.listOfProducts.shouldHave(CollectionCondition.size(1));
        CartPage.productActionsButton.click();
        CartPage.deleteProductButton.click();
        CartPage.cartEmptyState.shouldBe(Condition.visible);
    }

    @Test
    public void test2() {
        MainPage.search(apple);
        MainPage.listOfCategories.shouldHave(CollectionCondition.size(20));
        MainPage.listOfCategories.get(0).click();
        MainPage.catalogHeader.shouldHave(Condition.text(apple));
    }

    @Test
    public void test3() {
        MainPage.search(iphone13);
        MainPage.seriesFilters.find(Condition.text("iPhone 13")).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
        int countOfGoods = MainPage.listOfGoods.size();
        MainPage.sellerFilters.find(Condition.text("Rozetka")).click();
        MainPage.listOfGoods.shouldHave(CollectionCondition.sizeLessThan(countOfGoods)); // test fails because count of goods not changes
    }

    @Test
    public void test4() throws InterruptedException {
        MainPage.search(iphone13);
        Thread.sleep(5000);
        int height = MainPage.listOfGoods.get(0).getSize().getHeight();
        int width = MainPage.listOfGoods.get(0).getSize().getWidth();
        Assert.assertEquals(height, MainPage.listOfGoods.get(0).getSize().getHeight());
        Assert.assertEquals(width, MainPage.listOfGoods.get(0).getSize().getWidth());
        MainPage.bigTileButton.click();
        Thread.sleep(5000);
        height = MainPage.listOfGoods.get(0).getSize().getHeight();
        width = MainPage.listOfGoods.get(0).getSize().getWidth();
        Assert.assertEquals(height, MainPage.listOfGoods.get(0).getSize().getHeight());
        Assert.assertEquals(width, MainPage.listOfGoods.get(0).getSize().getWidth());
    }

    @Test
    public void test5() throws InterruptedException {
        MainPage.search(iphone);
        Thread.sleep(8000);
        MainPage.sorting.selectOptionByValue("2: expensive");
        Thread.sleep(8000);
        int firstProductValue = Integer.parseInt(MainPage.productPrice.get(0).getText().replaceAll(" ", "").replaceAll("₴", ""));
        int secondProductValue = Integer.parseInt(MainPage.productPrice.get(1).getText().replaceAll(" ", "").replaceAll("₴", ""));
        Assert.assertTrue(firstProductValue > secondProductValue);
    }
}
