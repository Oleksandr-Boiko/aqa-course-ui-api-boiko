package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public static SelenideElement cartModalName = $("div.modal__header");
    public static SelenideElement cartEmptyState = $("[data-testid='empty-cart']");
    public static ElementsCollection listOfProducts = $$("li.cart-list__item");
    public static SelenideElement productActionsButton = $("[id='cartProductActions0']");
    public static SelenideElement deleteProductButton = $("rz-trash-icon");
    public static SelenideElement closeModalButton = $("button.modal__close");

    public static void closeCart() {
        closeModalButton.click();
        closeModalButton.shouldBe(Condition.hidden);
    }

    public static void deleteProductFromCard() {
        productActionsButton.click();
        deleteProductButton.click();
    }

    public static void checkSizeOfCart(int expectedSize) {
        listOfProducts.shouldHave(CollectionCondition.size(expectedSize));
    }

    public static void assertCartIsEmpty() {
        cartEmptyState.shouldBe(Condition.visible);
    }

    public static void assertCartIsNotEmpty() {
        cartEmptyState.shouldBe(Condition.hidden);
    }
}
