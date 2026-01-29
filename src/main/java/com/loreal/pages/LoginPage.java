package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.xpath("//input[@data-test='username' or @id='user-name']");
    private final By passwordField = By.xpath("//input[@data-test='password' or @id='password']");
    private final By loginButton = By.xpath("//input[@data-test='login-button' or @id='login-button' or (self::button and contains(.,'Login'))]");
    private final By errorMessage = By.xpath("//h3[@data-test='error' or contains(@class,'error') or contains(normalize-space(),'Epic sadface')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoaded() {
        return isVisible(usernameField) && isVisible(passwordField) && isVisible(loginButton);
    }

    public boolean isErrorDisplayed() {
        return isVisible(errorMessage);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }
}
