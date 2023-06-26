package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    public static String baseURL = "https://rozetka.com.ua/";
    public static SelenideElement cartButton = $("li.header-actions__item--cart button");
    public static SelenideElement cartBadgeIcon = $("span.badge");
    public static SelenideElement searchField = $("input[name='search']");

    @Step("Searching")
    public static void search(String text) {
        MainPage.searchField.click();
        MainPage.searchField.setValue(text);
        MainPage.searchField.pressEnter();
    }

    public static void openCart() {
        cartButton.click();
        CartPage.cartModalName.shouldBe(Condition.text("Корзина"));
    }
}
