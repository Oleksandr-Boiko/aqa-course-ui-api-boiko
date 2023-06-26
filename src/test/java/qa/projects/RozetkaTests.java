package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ListOfGoodsPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pages.CartPage.*;
import static pages.ListOfGoodsPage.*;
import static pages.MainPage.openCart;
import static pages.MainPage.search;

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
        openCart();
        assertCartIsEmpty();
        closeCart();
        search(iphone);
        checkSizeOfListOfGoods(29);
        addGoodToCart(0);
        openCart();
        assertCartIsNotEmpty();
        checkSizeOfCart(1);
        deleteProductFromCard();
        assertCartIsEmpty();
    }

    @Test(description = "Check list of categories")
    public void test2() {
        search(apple);
        checkSizeOfListOfGoods(20);
        selectCategory(0);
        checkCategoryHeader(apple);
    }

    @Test(description = "Check filters functionality")
    public void test3() {
        search(iphone13);
        assertSeriesFilterIsChecked("iPhone 13");
        int countOfGoods = ListOfGoodsPage.listOfGoods.size();
        selectSellerFilter("Rozetka");
        selectSeriesFilter("iPhone 14");
        ListOfGoodsPage.listOfGoods.shouldBe(CollectionCondition.sizeGreaterThan(countOfGoods));
    }

    @Test(description = "Check size switcher of goods")
    public void test4() {
        search(iphone13);
        checkListOfGoodsLoaded();
        int smallGoodsHeight = 455;
        int smallGoodsWidth = 207;
        Assert.assertEquals(smallGoodsHeight, ListOfGoodsPage.getProductHeight(0), "height not equals");
        Assert.assertEquals(smallGoodsWidth, ListOfGoodsPage.getProductWidth(0), "width not equals");
        switchToBigTile();
        int bigGoodsHeight = 523;
        int bigGoodsWidth = 259;
        Assert.assertEquals(bigGoodsHeight, ListOfGoodsPage.getProductHeight(0), "height not equals");
        Assert.assertEquals(bigGoodsWidth, ListOfGoodsPage.getProductWidth(0), "width not equals");
    }

    @Test(description = "Check sorting functionality")
    public void test5() {
        search(iphone);
        checkListOfGoodsLoaded();
        assertSortingValue("4: rank");
        selectSortingValue(sortingValue);
        assertSortingValue(sortingValue);
        assertProducerFilterIsChecked(apple);
        checkListOfGoodsLoaded();
        int firstProductValue = ListOfGoodsPage.getProductPrice(0);
        int secondProductValue = ListOfGoodsPage.getProductPrice(1);
        Assert.assertTrue(firstProductValue > secondProductValue, "first product price not bigger then second product price");
    }
}
