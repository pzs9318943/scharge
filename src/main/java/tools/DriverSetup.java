package tools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverSetup {
    public static WebDriver driver;
    public static CustomActions actions;

    @Parameters({"browser", "browserWidth", "browserHeight"})
    @Test(priority = 1, testName = "DS01", description = "Driver setup, Resolution change, Actions setup")
    public void setup(String browser, String browserWidth, String browserHeight){
        // Setup driver
        driver = DriverSetup.create(browser);

        // Set resolution
        Dimension dimension = new Dimension(Integer.parseInt(browserWidth), Integer.parseInt(browserHeight));
        driver.manage().window().setSize(dimension);

        // Setup custom actions
        actions = new CustomActions(driver);

        // Open site
        actions.openSite("https://www.saucedemo.com/");
    }

    private static WebDriver create(String browser){
        WebDriver driver = null;

        // Create driver using WDM
        if(browser.equalsIgnoreCase("Chrome")){
            driver = WebDriverManager.chromedriver().create();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = WebDriverManager.edgedriver().create();
        }

        return driver;
    }
}
