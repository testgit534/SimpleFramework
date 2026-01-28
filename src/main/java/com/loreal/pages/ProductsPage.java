package com.loreal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By title = By.xpath("//span[@data-test='title' and normalize-space()='Products']");
    private final By inventoryList = By.xpath("//div[contains(@class,'inventory_list')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(title) && isVisible(inventoryList);
    }

    public void addProductToCartByName(String productName) {
        By addButton = By.xpath("//div[@class='inventory_item_name ' or @class='inventory_item_name'][normalize-space()='" + productName + "']" +
                "/ancestor::div[contains(@class,'inventory_item')]//button[contains(@data-test,'add-to-cart') or contains(normalize-space(),'Add to cart')]");
        click(addButton);
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }
}
