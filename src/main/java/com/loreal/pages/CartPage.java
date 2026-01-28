package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By cartTitle = By.xpath("//span[@data-test='title' and normalize-space()='Your Cart']");
    private final By checkoutButton = By.xpath("//button[@data-test='checkout' or @id='checkout' or @name='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(cartTitle);
    }

    public boolean isProductPresent(String productName) {
        By item = By.xpath("//div[contains(@class,'cart_item')]//div[@class='inventory_item_name' and normalize-space()='" + productName + "']");
        return isVisible(item);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}
