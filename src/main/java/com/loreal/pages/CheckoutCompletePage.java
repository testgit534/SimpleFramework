package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By title = By.xpath("//span[@data-test='title' and normalize-space()='Checkout: Complete!']");
    private final By thankYouHeader = By.xpath("//h2[@data-test='complete-header' or self::h2][contains(normalize-space(),'Thank you')]");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(title) && isVisible(thankYouHeader);
    }

    public String getThankYouMessage() {
        return getText(thankYouHeader);
    }
}
