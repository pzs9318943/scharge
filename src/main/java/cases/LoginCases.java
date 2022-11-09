package cases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage;
import tools.GlobalRules;

import static tools.DriverSetup.actions;
import static tools.DriverSetup.driver;

public class LoginCases extends GlobalRules {
    @Test(priority = 1, testName = "LT01", description = "Normal login")
    public void lt01(){
        // Login
        LoginPage loginPage = new LoginPage();
        loginPage.action_login("standard_user", "secret_sauce");

        // Landing, assertion
        String currentUrl = actions.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");

        // Logout
        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.action_logout();
    }

    @Test(priority = 2, testName = "LT02", description = "Failed login (no password)")
    public void lt02(){
        LoginPage loginPage = new LoginPage();

        // Login
        loginPage.action_login("standard_user", "");

        // Error message assertion
        String error_actual = loginPage.get_errorMessage();
        actions.log("Received error message: "+error_actual);
        String error_expected = "Epic sadface: Password is required";
        Assert.assertEquals(error_expected, error_actual);
    }

    @Test(priority = 3, testName = "LT03", description = "Failed test (expected error)")
    public void lt03(){
        LoginPage loginPage = new LoginPage();

        // Reset login
        loginPage.action_resetLogin();

        // Login
        loginPage.action_login("standard_user", "secret_sauce");

        // Error message assertion
        String error_actual = loginPage.get_errorMessage();
        actions.log("Received error message: "+error_actual);
        String error_expected = "Epic sadface: Username is required";
        Assert.assertEquals(error_expected, error_actual);
    }
}
