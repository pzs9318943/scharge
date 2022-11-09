package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import tools.CustomActions;
import org.openqa.selenium.WebDriver;

import static tools.DriverSetup.actions;
import static tools.DriverSetup.driver;

public class InventoryPage {
    // Elements
    @FindBy(id = "react-burger-menu-btn")
    private WebElement button_menu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement link_logout;

    @FindBys({@FindBy(id = "add-to-cart-sauce-labs-backpack")})
    private WebElement button_add_slBackpack;

    @FindBys({@FindBy(id = "add-to-cart-sauce-labs-bike-light")})
    private WebElement button_add_slBikeLight;

    @FindBys({@FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")})
    private WebElement button_add_slBoltTshirt;

    @FindBys({@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")})
    private WebElement button_add_slFleeceJacket;

    @FindBys({@FindBy(id = "add-to-cart-sauce-labs-onesie")})
    private WebElement button_add_slOnesie;

    @FindBys({@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")})
    private WebElement button_add_allTheThings;

    @FindBys({@FindBy(id = "remove-sauce-labs-backpack")})
    private WebElement button_remove_slBackpack;

    @FindBys({@FindBy(id = "remove-sauce-labs-bike-light")})
    private WebElement button_remove_slBikeLight;

    @FindBys({@FindBy(id = "remove-sauce-labs-bolt-t-shirt")})
    private WebElement button_remove_slBoltTshirt;

    @FindBys({@FindBy(id = "remove-sauce-labs-fleece-jacket")})
    private WebElement button_remove_slFleeceJacket;

    @FindBys({@FindBy(id = "remove-sauce-labs-onesie")})
    private WebElement button_remove_slOnesie;

    @FindBys({@FindBy(id = "remove-test.allthethings()-t-shirt-(red)")})
    private WebElement button_remove_allTheThings;

    public InventoryPage(){
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void action_logout(){
        actions.click(button_menu);
        actions.click(link_logout);
    }
    public void click_addToCart(String itemName){
        switch (itemName){
            case "Sauce Labs Backpack" -> actions.click(button_add_slBackpack);
            case "Sauce Labs Bike Light" -> actions.click(button_add_slBikeLight);
            case "Sauce Labs Bolt T-Shirt" -> actions.click(button_add_slBoltTshirt);
            case "Sauce Labs Fleece Jacket" -> actions.click(button_add_slFleeceJacket);
            case "Sauce Labs Onesie" -> actions.click(button_add_slOnesie);
            case "Test.allTheThings() T-Shirt (Red)" -> actions.click(button_add_allTheThings);
        }
    }
    public void click_remove(String itemName){
        switch (itemName){
            case "Sauce Labs Backpack" -> actions.click(button_remove_slBackpack);
            case "Sauce Labs Bike Light" -> actions.click(button_remove_slBikeLight);
            case "Sauce Labs Bolt T-Shirt" -> actions.click(button_remove_slBoltTshirt);
            case "Sauce Labs Fleece Jacket" -> actions.click(button_remove_slFleeceJacket);
            case "Sauce Labs Onesie" -> actions.click(button_remove_slOnesie);
            case "Test.allTheThings() T-Shirt (Red)" -> actions.click(button_remove_allTheThings);
        }
    }
    public double click_addToCart_inRange(String itemName, double current_cartValue, double min_cartValue, double max_cartValue){
        actions.log("Current cart value: "+current_cartValue);

        double price = Double.parseDouble(actions.getItemPrice(itemName));
        if((current_cartValue < min_cartValue) && (current_cartValue + price < max_cartValue)){
            current_cartValue += price;
            click_addToCart(itemName);
            actions.log("Added to cart.");
        }else{
            actions.log("Adding this item would exceed the maximum range. ("+max_cartValue+")");
        }

        return current_cartValue;
    }
}
