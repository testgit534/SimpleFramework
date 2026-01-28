package com.loreal.test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

public class SauceDemoCheckoutE2ETest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(SauceDemoCheckoutE2ETest.class);

    @Test
    public void endToEndCheckoutFlow() {
        String productName = "Sauce Labs Backpack";

        Reporter.log("Step 1: Login with valid credentials", true);
        log.info("Navigating to login and authenticating");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Reporter.log("Step 2: Verify Products page is loaded", true);
        log.info("Verifying Products page load");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded(), "Products page did not load as expected.");

        Reporter.log("Step 3: Add product to cart via XPath-based locator", true);
        log.info("Adding product to cart: {}", productName);
        productsPage.addProductToCartByName(productName);

        Reporter.log("Step 4: Verify cart badge count increments to 1", true);
        log.info("Validating cart badge shows 1");
        String badgeCount = productsPage.header().getCartBadgeCount();
        Assert.assertEquals(badgeCount, "1", "Cart badge count mismatch after adding product.");

        Reporter.log("Step 5: Open Cart and validate item presence", true);
        log.info("Opening cart and verifying the product is present");
        productsPage.header().openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isLoaded(), "Cart page did not load.");
        Assert.assertTrue(cartPage.isProductPresent(productName), "Expected product not found in cart.");

        Reporter.log("Step 6: Proceed to Checkout - Your Information", true);
        log.info("Clicking checkout");
        cartPage.clickCheckout();
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        Assert.assertTrue(infoPage.isLoaded(), "Checkout Information page did not load.");

        Reporter.log("Step 7: Fill in checkout information and continue", true);
        log.info("Filling checkout information");
        infoPage.fillInformation("John", "Doe", "12345");
        infoPage.clickContinue();

        Reporter.log("Step 8: Verify Checkout Overview and product presence", true);
        log.info("Validating overview page and product");
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(overviewPage.isLoaded(), "Checkout Overview page did not load.");
        Assert.assertTrue(overviewPage.isProductPresent(productName), "Product not present on Checkout Overview page.");

        Reporter.log("Step 9: Finish checkout and validate completion", true);
        log.info("Finishing checkout");
        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isLoaded(), "Checkout Complete page did not load.");
        String thankYou = completePage.getThankYouMessage();
        Assert.assertTrue(thankYou.contains("Thank you"), "Thank you message not displayed as expected.");

        Reporter.log("E2E Checkout flow completed successfully.", true);
        log.info("End-to-end checkout test completed successfully");
    }
}
