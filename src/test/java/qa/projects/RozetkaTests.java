package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ListOfGoodsPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pages.ListOfGoodsPage.checkListOfGoodsLoaded;

public class RozetkaTests {

    private String iphone = "iphone";
    private String iphone13 = "iphone 13";
    private String apple = "Apple";
    private String sortingValue = "2: expensive";

    @BeforeClass
    public void openSite() {
        Configuration.browserSize = "1366x768";
        open(MainPage.baseURL);
    }

    @BeforeMethod
    public void returnHomePage() {
        open(MainPage.baseURL);
    }

    @Test(description = "Actions with cart")
    public void test1() {
        MainPage.cartButton.click();
        CartPage.cartModalName.shouldBe(Condition.text("Корзина"));
        CartPage.cartEmptyState.shouldBe(Condition.visible);
        CartPage.closeModalButton.click();
        MainPage.search(iphone);
        ListOfGoodsPage.listOfGoods.shouldHave(CollectionCondition.size(28));
        ListOfGoodsPage.buyButton.get(0).hover().click();
        MainPage.cartBadgeIcon.shouldBe(Condition.text("1"));
        MainPage.cartButton.click();
        CartPage.cartEmptyState.shouldBe(Condition.hidden);
        CartPage.listOfProducts.shouldHave(CollectionCondition.size(1));
        CartPage.productActionsButton.click();
        CartPage.deleteProductButton.click();
        CartPage.cartEmptyState.shouldBe(Condition.visible);
    }

    @Test(description = "Check list of categories")
    public void test2() {
        MainPage.search(apple);
        ListOfGoodsPage.listOfCategories.shouldHave(CollectionCondition.size(20));
        ListOfGoodsPage.listOfCategories.get(0).click();
        ListOfGoodsPage.catalogHeader.shouldHave(Condition.text(apple));
    }

    @Test(description = "Check filters functionality")
    public void test3() {
        MainPage.search(iphone13);
        ListOfGoodsPage.seriesFilters.find(Condition.text("iPhone 13")).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
        int countOfGoods = ListOfGoodsPage.listOfGoods.size();
        ListOfGoodsPage.sellerFilters.find(Condition.text("Rozetka")).click();
        ListOfGoodsPage.seriesFilters.find(Condition.text("iPhone 14")).click();
        ListOfGoodsPage.listOfGoods.shouldBe(CollectionCondition.sizeGreaterThan(countOfGoods));
    }

    @Test(description = "Check size switcher of goods")
    public void test4() {
        MainPage.search(iphone13);
        checkListOfGoodsLoaded();
        int smallGoodsheight = 455;
        int smallGoodswidth = 207;
        Assert.assertEquals(smallGoodsheight, ListOfGoodsPage.listOfGoods.get(0).getSize().getHeight(), "height not equals");
        Assert.assertEquals(smallGoodswidth, ListOfGoodsPage.listOfGoods.get(0).getSize().getWidth(), "width not equals");
        ListOfGoodsPage.bigTileButton.click();
        ListOfGoodsPage.bigTileButton.shouldHave(Condition.cssClass("catalog-view__button_state_active"));
        int bigGoodsheight = 523;
        int bigGoodswidth = 259;
        Assert.assertEquals(bigGoodsheight, ListOfGoodsPage.listOfGoods.get(0).getSize().getHeight(), "height not equals");
        Assert.assertEquals(bigGoodswidth, ListOfGoodsPage.listOfGoods.get(0).getSize().getWidth(), "width not equals");
    }

    @Test(description = "Check sorting functionality")
    public void test5() {
        MainPage.search(iphone);
        checkListOfGoodsLoaded();
        ListOfGoodsPage.sorting.shouldBe(Condition.value("4: rank"));
        ListOfGoodsPage.sorting.selectOptionByValue(sortingValue);
        ListOfGoodsPage.sorting.shouldBe(Condition.value(sortingValue));
        ListOfGoodsPage.producerFilters.find(Condition.text(apple)).shouldBe(Condition.visible);
        ListOfGoodsPage.producerFilters.find(Condition.text(apple)).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
        checkListOfGoodsLoaded();
        int firstProductValue = Integer.parseInt(ListOfGoodsPage.productPrice.get(0).getText().replaceAll("[^0-9]", ""));
        int secondProductValue = Integer.parseInt(ListOfGoodsPage.productPrice.get(1).getText().replaceAll("[^0-9]", ""));
        Assert.assertTrue(firstProductValue > secondProductValue, "first product price not bigger then second product price");
    }
}
