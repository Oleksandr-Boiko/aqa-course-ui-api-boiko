package pages;

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

}
