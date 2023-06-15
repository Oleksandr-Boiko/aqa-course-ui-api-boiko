package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public static String baseURL = "https://rozetka.com.ua/";
    public static SelenideElement cartButton = $("li.header-actions__item--cart button");
    public static SelenideElement cartBadgeIcon = $("span.badge");
    public static SelenideElement searchField = $("input[name='search']");
    public static SelenideElement searchButton = $("button.search-form__submit");
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

    public static void search(String text) {
        MainPage.searchField.click();
        MainPage.searchField.setValue(text);
        MainPage.searchField.pressEnter();
    }

    public static void checkListOfGoodsLoaded() {
        MainPage.listOfGoods.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(5));
        MainPage.buyButton.get(0).shouldBe(Condition.visible);
    }
}
