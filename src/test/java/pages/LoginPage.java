package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.Driver;

public class LoginPage {

    WebDriver driver;

    public LoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login-username")
    public WebElement usernameInput;

    @FindBy(id = "login-password")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id='login-form']/div/button")
    public WebElement loginButton;

    public void login(){
        usernameInput.sendKeys(ConfigReader.getProperty("elarUsername"));
        passwordInput.sendKeys(ConfigReader.getProperty("elarPassword"));
        loginButton.click();
    }

}
