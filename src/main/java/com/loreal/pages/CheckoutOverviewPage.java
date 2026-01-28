package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By title = By.xpath("//span[@data-test='title' and normalize-space()='Checkout: Overview']");
    private final By finishButton = By.xpath("//button[@data-test='finish' or @id='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(title);
    }

    public boolean isProductPresent(String productName) {
        By item = By.xpath("//div[contains(@class,'cart_item')]//div[@class='inventory_item_name' and normalize-space()='" + productName + "']");
        return isVisible(item);
    }

    public void clickFinish() {
        click(finishButton);
    }
}
