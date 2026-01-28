package com.loreal.test;

import base.BaseTest;
import com.loreal.pages.CartPage;
import com.loreal.pages.CheckoutCompletePage;
import com.loreal.pages.CheckoutInformationPage;
import com.loreal.pages.CheckoutOverviewPage;
import com.loreal.pages.HeaderComponent;
import com.loreal.pages.ProductsPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.LoginPage;

public class EndToEndPurchaseTest extends BaseTest {

    @Test
    public void verifyUserCanCompleteCheckoutFlow() {
        String username = "standard_user";
        String password = "secret_sauce";
        String productName = "Sauce Labs Backpack";
        String firstName = "Jane";
        String lastName = "Doe";
        String zipCode = "90210";

        Reporter.log("Step 1: Log in to SauceDemo with valid credentials", true);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        Reporter.log("Step 2: Verify Products page is loaded", true);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded(), "Products page failed to load.");

        Reporter.log("Step 3: Add product to cart: " + productName, true);
        productsPage.addProductToCartByName(productName);

        Reporter.log("Step 4: Validate cart badge increments after adding product", true);
        HeaderComponent header = productsPage.header();
        int cartCount = header.getCartCount();
        Assert.assertTrue(cartCount >= 1, "Expected cart count >= 1 after adding product, but was: " + cartCount);

        Reporter.log("Step 5: Navigate to Cart", true);
        header.openCart();

        Reporter.log("Step 6: Verify Cart page is loaded and product is present", true);
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isLoaded(), "Cart page failed to load.");
        Assert.assertTrue(cartPage.isProductPresent(productName), "Product not found in cart: " + productName);

        Reporter.log("Step 7: Click Checkout", true);
        cartPage.clickCheckout();

        Reporter.log("Step 8: Fill in Checkout Information and continue", true);
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(infoPage.isLoaded(), "Checkout Information page failed to load.");
        infoPage.fillInformation(firstName, lastName, zipCode);
        infoPage.clickContinue();

        Reporter.log("Step 9: Verify Checkout Overview page is loaded and product is present", true);
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(overviewPage.isLoaded(), "Checkout Overview page failed to load.");
        Assert.assertTrue(overviewPage.isProductPresent(productName), "Product not listed in Checkout Overview: " + productName);

        Reporter.log("Step 10: Finish checkout", true);
        overviewPage.clickFinish();

        Reporter.log("Step 11: Validate Checkout Complete page and thank you message", true);
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isLoaded(), "Checkout Complete page failed to load.");
        String thankYou = completePage.getThankYouMessage();
        Assert.assertTrue(thankYou != null && thankYou.toLowerCase().contains("thank"),
                "Expected a thank you message on completion, but got: " + thankYou);

        Reporter.log("Checkout flow completed successfully. Thank you message: " + thankYou, true);
    }
}
