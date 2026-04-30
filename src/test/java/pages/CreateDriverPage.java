package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.security.cert.X509Certificate;

public class CreateDriverPage {

    WebDriver driver;

    public CreateDriverPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='is_staff']")
    public WebElement isStaff;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullNameInput;

    @FindBy(name = "driving_license_exp")
    public WebElement driverLicenseExp;

    @FindBy(name = "medical_certification_exp")
    public WebElement medicalCertificationExp;

    @FindBy(xpath = "//button[text()='Create new']")
    public WebElement createNwBtn;

    @FindBy(xpath = "//button[text()='Go to Edit']")
    public WebElement goToEditBtn;


}
