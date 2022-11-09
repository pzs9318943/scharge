package cases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import tools.GlobalRules;

import static tools.DriverSetup.actions;

public class InventoryCases extends GlobalRules {
    InventoryPage inventoryPage;
    double min_cartValue;
    double max_cartValue;
    double current_cartValue = 0.00;

    @DataProvider(name = "items")
    public Object[][] dataObj(){
        return new Object[][] {{"Sauce Labs Backpack"}, {"Sauce Labs Bike Light"}, {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"}, {"Sauce Labs Onesie"}, {"Test.allTheThings() T-Shirt (Red)"}};
    }

    @Parameters({"priceRangeMin", "priceRangeMax"})
    @Test(priority = 1, testName = "IT00", description = "Login")
    public void login(String priceRangeMin, String priceRangeMax){
        LoginPage loginPage = new LoginPage();
        loginPage.action_login("standard_user", "secret_sauce");
        String currentUrl = actions.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        inventoryPage = new InventoryPage();
        min_cartValue = Double.parseDouble(priceRangeMin);
        max_cartValue = Double.parseDouble(priceRangeMax);
    }

    @Test(priority = 2, testName = "IT01", description = "Add to cart", dataProvider = "items")
    public void addToCart(String itemName){
        inventoryPage.click_addToCart(itemName);
    }

    @Test(priority = 3, testName = "IT02", description = "Remove from cart", dataProvider = "items")
    public void removeFromCart(String itemName){
        inventoryPage.click_remove(itemName);
    }

    @Test(priority = 4, testName = "IT03", description = "Add to cart in a price range (40-60 USD)", dataProvider = "items")
    public void addToCartInRange(String itemName){
        current_cartValue = inventoryPage.click_addToCart_inRange(itemName, current_cartValue, min_cartValue, max_cartValue);
    }
}
