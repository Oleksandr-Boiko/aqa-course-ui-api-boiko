package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pages.MainPage.checkListOfGoodsLoaded;

@Owner("Boiko Oleksandr")
@Epic("Rozetka")
public class RozetkaTests {

    private String iphone = "iphone";
    private String iphone13 = "iphone 13";
    private String apple = "Apple";
    private String sortingValue = "2: expensive";

    @BeforeClass
    public void openSite() {
        open(MainPage.baseURL);
    }

    @BeforeMethod
    public void returnHomePage() {
        open(MainPage.baseURL);
    }

    @Description("Check cart functionality")
    @Test(description = "Actions with cart")
    public void test1() {
        MainPage.cartButton.click();
        CartPage.cartModalName.shouldBe(Condition.text("Корзина"));
        CartPage.cartEmptyState.shouldBe(Condition.visible);
        CartPage.closeModalButton.click();
        MainPage.search(iphone);
        MainPage.listOfGoods.shouldHave(CollectionCondition.size(28));
        MainPage.buyButton.get(0).hover().click();
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
        MainPage.listOfCategories.shouldHave(CollectionCondition.size(20));
        MainPage.listOfCategories.get(0).click();
        MainPage.catalogHeader.shouldHave(Condition.text(apple));
    }

    @Test(description = "Check filters functionality")
    public void test3() {
        MainPage.search(iphone13);
        MainPage.seriesFilters.find(Condition.text("iPhone 13")).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
        int countOfGoods = MainPage.listOfGoods.size();
        MainPage.sellerFilters.find(Condition.text("Rozetka")).click();
        MainPage.seriesFilters.find(Condition.text("iPhone 14")).click();
        MainPage.listOfGoods.shouldBe(CollectionCondition.sizeGreaterThan(countOfGoods));
    }

    @Test(description = "Check size switcher of goods")
    public void test4() throws InterruptedException {
        MainPage.search(iphone13);
        checkListOfGoodsLoaded();
        int smallGoodsheight = 455;
        int smallGoodswidth = 207;
        Assert.assertEquals(smallGoodsheight, MainPage.listOfGoods.get(0).getSize().getHeight(), "height not equals");
        Assert.assertEquals(smallGoodswidth, MainPage.listOfGoods.get(0).getSize().getWidth(), "width not equals");
        MainPage.bigTileButton.click();
        MainPage.bigTileButton.shouldHave(Condition.cssClass("catalog-view__button_state_active"));
        int bigGoodsheight = 523;
        int bigGoodswidth = 259;
        Assert.assertEquals(bigGoodsheight, MainPage.listOfGoods.get(0).getSize().getHeight(), "height not equals");
        Assert.assertEquals(bigGoodswidth, MainPage.listOfGoods.get(0).getSize().getWidth(), "width not equals");
    }

    @Test(description = "Check sorting functionality")
    public void test5() throws InterruptedException {
        MainPage.search(iphone);
        checkListOfGoodsLoaded();
        MainPage.sorting.shouldBe(Condition.value("4: rank"));
        MainPage.sorting.selectOptionByValue(sortingValue);
        MainPage.sorting.shouldBe(Condition.value(sortingValue));
        MainPage.producerFilters.find(Condition.text(apple)).shouldBe(Condition.visible);
        MainPage.producerFilters.find(Condition.text(apple)).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
        int firstProductValue = Integer.parseInt(MainPage.productPrice.get(0).getText().replaceAll("[^0-9]", ""));
        int secondProductValue = Integer.parseInt(MainPage.productPrice.get(1).getText().replaceAll("[^0-9]", ""));
        Assert.assertTrue(firstProductValue > secondProductValue, "first product price not bigger then second product price");
    }
}
