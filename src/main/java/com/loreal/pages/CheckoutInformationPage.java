package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private final By title = By.xpath("//span[@data-test='title' and normalize-space()='Checkout: Your Information']");
    private final By firstName = By.xpath("//input[@data-test='firstName' or @id='first-name']");
    private final By lastName = By.xpath("//input[@data-test='lastName' or @id='last-name']");
    private final By postalCode = By.xpath("//input[@data-test='postalCode' or @id='postal-code']");
    private final By continueButton = By.xpath("//input[@data-test='continue' or @id='continue' or @name='continue' or (self::button and @data-test='continue')]");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(title);
    }

    public void fillInformation(String fName, String lName, String zip) {
        type(firstName, fName);
        type(lastName, lName);
        type(postalCode, zip);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
