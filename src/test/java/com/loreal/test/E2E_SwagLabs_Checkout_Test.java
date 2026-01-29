package com.loreal.test;

import base.BaseTest;
import com.loreal.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2E_SwagLabs_Checkout_Test extends BaseTest {

    @Test
    public void verifyEndToEndCheckoutFlow_UsingXPathAndPOM() {
        String productName = "Sauce Labs Backpack";
        String username = "standard_user";
        String password = "secret_sauce";

        System.out.println("[STEP] Logging into the application");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        System.out.println("[VERIFY] Products page is loaded");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded(), "Products page did not load successfully.");

        System.out.println("[STEP] Adding product to cart: " + productName);
        productsPage.addProductToCartByName(productName);

        System.out.println("[VERIFY] Cart badge increments to 1");
        HeaderComponent header = productsPage.header();
        int cartCount = header.getCartCount();
        Assert.assertEquals(cartCount, 1, "Cart count should be 1 after adding a product.");

        System.out.println("[STEP] Navigating to cart");
        header.openCart();

        System.out.println("[VERIFY] Cart page is loaded and product is present");
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isLoaded(), "Cart page did not load successfully.");
        Assert.assertTrue(cartPage.isProductPresent(productName), "Expected product not found in cart: " + productName);

        System.out.println("[STEP] Proceeding to checkout");
        cartPage.clickCheckout();

        System.out.println("[VERIFY] Checkout Information page is loaded");
        CheckoutInformationPage checkoutInfo = new CheckoutInformationPage(driver);
        Assert.assertTrue(checkoutInfo.isLoaded(), "Checkout: Your Information page did not load.");

        System.out.println("[STEP] Filling checkout information and continuing");
        checkoutInfo.fillInformation("John", "Doe", "12345");
        checkoutInfo.clickContinue();

        System.out.println("[VERIFY] Checkout Overview page is loaded and product is present");
        CheckoutOverviewPage overview = new CheckoutOverviewPage(driver);
        Assert.assertTrue(overview.isLoaded(), "Checkout: Overview page did not load.");
        Assert.assertTrue(overview.isProductPresent(productName), "Product is not listed on the overview page: " + productName);

        System.out.println("[STEP] Finishing checkout");
        overview.clickFinish();

        System.out.println("[VERIFY] Checkout Complete page is loaded with thank you message");
        CheckoutCompletePage complete = new CheckoutCompletePage(driver);
        Assert.assertTrue(complete.isLoaded(), "Checkout: Complete page did not load.");
        String thankYou = complete.getThankYouMessage();
        Assert.assertTrue(thankYou != null && thankYou.toLowerCase().contains("thank"), "Thank you message not displayed as expected.");

        System.out.println("[RESULT] End-to-end checkout flow completed successfully.");
    }
}
