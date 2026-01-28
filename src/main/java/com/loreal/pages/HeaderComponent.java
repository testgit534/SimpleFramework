package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    private final By cartLink = By.xpath("//a[@data-test='shopping-cart-link' or contains(@class,'shopping_cart_link')]");
    private final By cartBadge = By.xpath("//span[@data-test='shopping-cart-badge' or contains(@class,'shopping_cart_badge')]");
    private final By menuButton = By.xpath("//button[@id='react-burger-menu-btn' or @data-test='open-menu']");
    private final By logoutLink = By.xpath("//a[@id='logout_sidebar_link' or @data-test='logout-sidebar-link']");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartLink);
    }

    public int getCartCount() {
        if (isVisible(cartBadge)) {
            String text = getText(cartBadge);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public void openMenu() {
        click(menuButton);
    }

    public void logout() {
        openMenu();
        waitForVisible(logoutLink);
        click(logoutLink);
    }
}
