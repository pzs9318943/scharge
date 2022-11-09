package pages;

import org.openqa.selenium.By;
import tools.CustomActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static tools.DriverSetup.actions;
import static tools.DriverSetup.driver;

public class LoginPage {
    // Elements
    @FindBy(id = "user-name")
    private WebElement field_username;

    @FindBy(id = "password")
    private WebElement field_password;

    @FindBy(id = "login-button")
    private WebElement button_login;

    @FindBy(xpath = "//*[@data-test='error']")
    private WebElement message_error;

    @FindBy(className = "error-button")
    private WebElement button_error;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void fill_username(String username){
        actions.fill(field_username, username);
    }
    public void fill_password(String password){
        actions.fill(field_password, password);
    }
    public void click_login(){
        actions.click(button_login);
    }
    public void action_login(String username, String password){
        fill_username(username);
        fill_password(password);
        click_login();
    }
    public void action_resetLogin(){
        boolean error_visible = check_error();
        if(error_visible) {
            actions.click(button_error);
            actions.clear(field_username);
            actions.clear(field_password);
        }
    }
    public boolean check_error(){
        return driver.findElements(By.xpath("//*[@data-test='error']")).size() > 0;
    }
    public String get_errorMessage(){
        return message_error.getText();
    }
}
