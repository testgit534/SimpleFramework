package com.loreal.test;

import base.BaseTest;
import com.loreal.pages.LoginPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void verifyInvalidLoginShowsErrorMessage() {
        Reporter.log("Step 1: Navigate to Swag Labs login page", true);
        LoginPage loginPage = new LoginPage(driver);

        Reporter.log("Validation: Verify login page is loaded (username, password fields and login button visible)", true);
        Assert.assertTrue(loginPage.isLoaded(), "Login page did not load correctly.");

        Reporter.log("Step 2: Enter invalid credentials and attempt to login", true);
        String invalidUsername = "invalid_user";
        String invalidPassword = "wrong_password";
        loginPage.login(invalidUsername, invalidPassword);

        Reporter.log("Step 3: Validate error message is displayed for invalid credentials", true);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message was not displayed.");

        String actualError = loginPage.getErrorMessageText();
        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        Reporter.log("Captured error message: " + actualError, true);

        Assert.assertEquals(actualError, expectedError, "Error message text mismatch for invalid login.");

        Reporter.log("Validation: Ensure we remain on the login page after failed login attempt", true);
        Assert.assertTrue(loginPage.isLoaded(), "User should remain on the login page after an invalid login attempt.");
    }
}
