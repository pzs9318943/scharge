package tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static tools.DriverSetup.driver;

public class CustomActions {
    Actions actions;
    WebDriver driver;

    public CustomActions(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    /**
     * Quick logger for real-time feedback on what's happening
     * @param message message to be displayed
     */
    public void log(String message){
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("["+time+"] "+message);
    }

    /**
     * Scrolls to the element. Another solution could be using a small javascript snippet.
     * @param webElement element to scroll to
     */
    public void scrollTo(WebElement webElement){
        log("Scrolling to the element. ("+webElement.getAccessibleName()+")");
        actions.scrollToElement(webElement).perform();
    }

    /**
     * Scrolls to the element with an offset.
     * @param webElement element to scroll to
     * @param offset value of the scroll offset on the Y axis
     */
    public void scrollTo_offsetY(WebElement webElement, int offset){
        actions.scrollToElement(webElement).scrollByAmount(0, offset).perform();
        // TODO: teszt kell commit előtt, ebben nem vagyok biztos
    }

    /**
     * Takes a screenshot then copies the file into the "screenshots" directory
     */
    public void takeScreenshot(){
        log("Taking a screenshot.");
        String path = System.getProperty("user.dir") + "\\screenshots";
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFileToDirectory(file, new File(path));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Scrolls to the element, clicks on it then takes a screenshot.
     * @param webElement element to be clicked
     */
    public void click(WebElement webElement){
        scrollTo(webElement);
        log("Clicking on the element. ("+webElement.getAccessibleName()+")");
        webElement.click();
        takeScreenshot();
    }

    /**
     * Fills the input element with the data.
     * @param webElement element to fill
     * @param data value to fill with
     */
    public void fill(WebElement webElement, String data){
        scrollTo(webElement);
        log("Filling element ("+webElement.getAccessibleName()+") width data ("+data+").");
        webElement.sendKeys(data);
        takeScreenshot();
    }

    public void clear(WebElement webElement){
        scrollTo(webElement);
        log("Clearing element ("+webElement.getAccessibleName()+")");
        webElement.clear();
        takeScreenshot();
    }

    public void openSite(String url){
        log("Navigating to site. ("+url+")");
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(url, currentUrl);
    }

    public String getCurrentUrl(){
        String currentUrl = driver.getCurrentUrl();
        log("Retrieved current URL. ("+currentUrl+")");
        return currentUrl;
    }

    public String getText(WebElement webElement){
        String text = webElement.getText();
        log("Retrieved element text value. ("+text+")");
        return text;
    }

    public String getItemPrice(String itemName){
        // Search item
        boolean itemFound = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name') and text()='"+itemName+"']")).size() > 0;
        Assert.assertTrue(itemFound);

        // Price tag path
        String xpath = "//*[contains(@class, 'inventory_item_name') and text()='"+itemName+"']/ancestor::*[contains(@class, 'inventory_item_description')]//*[contains(@class, 'inventory_item_price')]";

        // Get price tag
        String price = driver.findElement(By.xpath(xpath)).getText();
        log("Retrieved item price. ("+itemName+": "+price+")");

        price = price.replaceAll("\\$", "");
        return price;
    }
}
