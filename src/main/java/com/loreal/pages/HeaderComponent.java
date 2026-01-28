package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    private final By cartLink = By.xpath("//a[contains(@class,'shopping_cart_link')]");
    private final By cartBadge = By.xpath("//a[contains(@class,'shopping_cart_link')]/span");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartLink);
    }

    public String getCartBadgeCount() {
        if (isVisible(cartBadge)) {
            return getText(cartBadge);
        }
        return "0";
    }
}
