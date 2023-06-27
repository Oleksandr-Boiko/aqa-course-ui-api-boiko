package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ListOfGoodsPage {

    public static SelenideElement catalogHeader = $("h1.catalog-heading");
    public static ElementsCollection listOfCategories = $$("li.portal-grid__cell");
    public static ElementsCollection listOfGoods = $$("li.catalog-grid__cell");
    public static ElementsCollection sellerFilters = $$("[data-filter-name='seller'] a.checkbox-filter__link");
    public static ElementsCollection seriesFilters = $$("[data-filter-name='series'] a.checkbox-filter__link");
    public static ElementsCollection producerFilters = $$("[data-filter-name='producer'] a.checkbox-filter__link");
    public static ElementsCollection productPrice = $$("span.goods-tile__price-value");
    public static ElementsCollection buyButton = $$("button.buy-button");
    public static SelenideElement bigTileButton = $("[arial-label='Крупная плитка']");
    public static SelenideElement sorting = $("rz-sort.catalog-settings__sorting select");

    public static void checkListOfGoodsLoaded() {
        listOfGoods.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(5));
        productPrice.get(0).shouldBe(Condition.visible);
        buyButton.get(0).shouldBe(Condition.visible);
    }

    public static void checkSizeOfListOfGoods(int expectedSize) {
        listOfGoods.shouldHave(CollectionCondition.size(expectedSize));
    }

    public static void addGoodToCart(int goodNumber) {
        buyButton.get(goodNumber).hover().click();
        MainPage.cartBadgeIcon.shouldBe(Condition.text("1"));
    }

    public static void selectCategory(int categoryNumber) {
        listOfCategories.get(categoryNumber).click();
    }

    public static void checkCategoryHeader(String name) {
        catalogHeader.shouldHave(Condition.text(name));
    }

    public static void selectSeriesFilter(String filterName) {
        seriesFilters.find(Condition.text(filterName)).click();
        assertSeriesFilterIsChecked(filterName);
    }

    public static void selectSellerFilter(String filterName) {
        sellerFilters.find(Condition.text(filterName)).click();
        assertSellerFilterIsChecked(filterName);
    }

    public static void assertSeriesFilterIsChecked(String filterName) {
        seriesFilters.find(Condition.text(filterName)).shouldBe(Condition.visible);
        seriesFilters.find(Condition.text(filterName)).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
    }

    public static void assertSellerFilterIsChecked(String filterName) {
        sellerFilters.find(Condition.text(filterName)).shouldBe(Condition.visible);
        sellerFilters.find(Condition.text(filterName)).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
    }

    public static void assertProducerFilterIsChecked(String filterName) {
        producerFilters.find(Condition.text(filterName)).shouldBe(Condition.visible);
        producerFilters.find(Condition.text(filterName)).shouldHave(Condition.cssClass("checkbox-filter__link--checked"));
    }

    public static void switchToBigTile() {
        bigTileButton.hover().click();
        bigTileButton.shouldHave(Condition.cssClass("catalog-view__button_state_active"));
    }

    public static int getProductHeight(int goodNumber) {
        int height = listOfGoods.get(goodNumber).getSize().getHeight();
        return height;
    }

    public static int getProductWidth(int goodNumber) {
        int width = listOfGoods.get(goodNumber).getSize().getWidth();
        return width;
    }

    public static void selectSortingValue(String name) {
        sorting.selectOptionByValue(name);
    }

    public static void assertSortingValue(String name) {
        sorting.shouldBe(Condition.value(name));
    }

    public static int getProductPrice(int productNumber) {
        int price = Integer.parseInt(ListOfGoodsPage.productPrice.get(productNumber)
                .getText().replaceAll("[^0-9]", ""));
        return price;
    }
}
