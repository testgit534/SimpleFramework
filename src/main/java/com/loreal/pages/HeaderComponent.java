package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    private final By cartLink = By.xpath("//a[@data-test='shopping-cart-link' or contains(@class,'shopping_cart_link')]");
    private final By cartBadge = By.xpath("//a[@data-test='shopping-cart-link' or contains(@class,'shopping_cart_link')]//span[contains(@class,'shopping_cart_badge')]");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartLink);
    }

    public int getCartCount() {
        if (isVisible(cartBadge)) {
            String count = getText(cartBadge);
            try {
                return Integer.parseInt(count);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
