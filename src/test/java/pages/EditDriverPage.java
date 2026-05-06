package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class EditDriverPage {


    WebDriver driver;

    public EditDriverPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Edit']")
    public WebElement editBtn;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullNameInput;

    @FindBy(xpath = "//button[text()='Update']")
    public WebElement updateBtn;

    @FindBy(xpath = "//label[text()='Manual id']/following-sibling::div/input")
    public WebElement driverId;

    @FindBy(xpath = "//button[text()='Back to list']")
    public WebElement backToListBtn;

    @FindBy(xpath = "//label[text()='Search...']/following-sibling::div/input")
    public WebElement searchResultName;

    @FindBy(xpath = "//input[@name='full_name']/../following-sibling::p")
    public WebElement nameErrorMessage;

        @FindBy(xpath = "//div/h6[text()='Medical cert document']/following-sibling::label")
        public  WebElement addMCDocument;

}
