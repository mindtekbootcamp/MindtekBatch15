package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.TestBase;

public class CreateNewDriver extends TestBase {

    @Test
    public void createDriverAsStaffTest() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id='login-form']/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        // CHECKBOX MARKET
        driver.findElement(By.xpath("//input[@value='is_staff']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Josh King2");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        // CREATE NEW BTN
        driver.findElement(By.xpath("//button[text()='Create new']")).click();
        //GO TO EDIT BTN
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();
        // Get Driver ID
        String driverId = driver.findElement(By.xpath("//label[text()='Manual id']/following-sibling::div/input")).getAttribute("value");
        //BACK LIST BTN
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();
        // Search for driver with ID
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(driverId + Keys.ENTER);
        // Getting driver id from search result
        Thread.sleep(2000);
        String actualDriverId = driver.findElement(By.xpath("//div[@data-field='driver_number']/a")).getText();
        Assert.assertEquals(actualDriverId, driverId);
    }

    @Test
    public void createDriverAsNonStaffTest() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Josh King2");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        // CREATE NEW BTN
        driver.findElement(By.xpath("//button[text()='Create new']")).click();
        //GO TO EDIT BTN
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();
        // Get Driver ID
        String driverId = driver.findElement(By.xpath("//label[text()='Manual id']/following-sibling::div/input")).getAttribute("value");
        //BACK LIST BTN
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();
        // Search for driver with ID
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(driverId + Keys.ENTER);
        // Click on non staff result
        driver.findElement(By.xpath("//div[text()='Non staff']")).click();
        // Getting driver id from search result
        Thread.sleep(2000);
        String actualDriverId = driver.findElement(By.xpath("//div[@data-field='driver_number']/a")).getText();
        Assert.assertEquals(actualDriverId, driverId);
    }

    @Test
    public void createDriverWithInvalidNameMoreThan50Chars() {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("usernameinvalidusernameinvalidusernameinvalidusernameinvalid");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[2]/div/div/div/form/div[19]/button")).click();
        String expectedResult = "String must contain at most 50 character(s)";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"uniforms-0003-0002-helper-text\"]")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void createDriverWithValidNameMinChars() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("M");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        // CREATE NEW BTN
        driver.findElement(By.xpath("//button[text()='Create new']")).click();
        //GO TO EDIT BTN
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();
        // Get Driver ID
        String driverId = driver.findElement(By.xpath("//label[text()='Manual id']/following-sibling::div/input")).getAttribute("value");
        //BACK LIST BTN
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();
        // Search for driver with ID
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(driverId + Keys.ENTER);
        // Click on non staff result
        driver.findElement(By.xpath("//div[text()='Non staff']")).click();
        // Getting driver id from search result
        Thread.sleep(2000);
        String actualDriverId = driver.findElement(By.xpath("//div[@data-field='driver_number']/a")).getText();
        // Validate driver Id
        Assert.assertEquals(actualDriverId, driverId);
    }

    @Test
    public void createDriverWithNoNameTest() {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[2]/div/div/div/form/div[19]/button")).click();
        String expectedResult = "Required";
        String actualResult = driver.findElement(By.xpath("//input[@name='full_name']/../following-sibling::p")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void createDriverWithSpecialCharsNameTest() {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("@#$%^&");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[2]/div/div/div/form/div[19]/button")).click();
        String expectedResult = "Input must contain only alphanumeric and specific punctuation characters";
        String actualResult = driver.findElement(By.xpath("//input[@name='full_name']/../following-sibling::p")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void createDriverLocalTestPositive() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Daniel Doe");
        driver.findElement(By.name("is_local")).click();
        driver.findElement(By.xpath("//div[@role='combobox']")).click();
        String selectedState = "NY";
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-value='" + selectedState + "']")).click();
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[2]/div/div/div/form/div[19]/button")).click();
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();
        // Get Driver ID
        String driverId = driver.findElement(By.xpath("//label[text()='Manual id']/following-sibling::div/input")).getAttribute("value");
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();
        // Search for driver with ID
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(driverId + Keys.ENTER);
        // Click on non staff result
        driver.findElement(By.xpath("//div[text()='Non staff']")).click();
        // Getting driver id from search result
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@data-field='driver_number']/a")).click();
        String actualSelectedState = driver.findElement(By.xpath("//label[text()='Local state *']/following-sibling::div/div")).getText();
        Assert.assertEquals(actualSelectedState, selectedState);
    }
    @Test
    public void createDriverPhoneTestPositive() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Daniel Doe");
        driver.findElement(By.xpath("//p[text()='Phone']/following-sibling::*[1]")).click();
        String phoneNumber=CommonMethods.generatePhoneNumber();
        driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//input[@type='string']")).sendKeys("256");
        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//button[text()='Create new']")).click();
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();
        String extPhone = driver.findElement(By.xpath("//input[@placeholder='Ext.']")).getAttribute("value");
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();

        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//button[text()='Email/Phone']")).click();
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(phoneNumber + Keys.ENTER);
        driver.findElement(By.xpath("//div[text()='Non staff']")).click();
        Thread.sleep(2000);
        String phoneExpecte = "+1 "+phoneNumber;
        String actualPhoneNumber=driver.findElement(By.xpath("//div[@data-field='phone' and @role='cell']/div")).getText();

        Assert.assertEquals(actualPhoneNumber,phoneExpecte);
    }

    @Test
    public void createDriverEmailTestPositive() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id='login-form']/div/button")).click();
        driver.findElement(By.xpath("//a[@href='/drivers/list']")).click();
        driver.findElement(By.xpath("//button[text()='Add driver']")).click();
        driver.findElement(By.xpath("//input[@name='full_name']")).sendKeys("Daniel Doe");
        // CLICK IN EMAIL + TO ADD EMAIL
        driver.findElement(By.xpath("//p[text()='Email']/following-sibling::*[1]")).click();

        String emailUser = CommonMethods.generateRamdomEmail();
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(emailUser);

        WebElement driverLicExp = driver.findElement(By.name("driving_license_exp"));
        driverLicExp.click();
        driverLicExp.sendKeys("07232028");
        WebElement medicalLicExp = driver.findElement(By.name("medical_certification_exp"));
        medicalLicExp.click();
        medicalLicExp.sendKeys("07232028");
        driver.findElement(By.xpath("//button[text()='Create new']")).click();
        driver.findElement(By.xpath("//button[text()='Go to Edit']")).click();

        String expectedEmail = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");
        driver.findElement(By.xpath("//button[text()='Back to list']")).click();

        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).click();
        driver.findElement(By.xpath("//button[text()='Email/Phone']")).click();
        driver.findElement(By.xpath("//label[text()='Search...']/following-sibling::div/input")).sendKeys(emailUser + Keys.ENTER);
        driver.findElement(By.xpath("//div[text()='Non staff']")).click();

        Assert.assertEquals(emailUser,expectedEmail);

    }
}
