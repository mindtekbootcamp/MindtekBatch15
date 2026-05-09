package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateDriverPage;
import pages.DriverHomePage;
import pages.EditDriverPage;
import pages.LoginPage;
import utils.BrowserUtils;
import utils.ConfigReader;
import utils.TestBase;

public class EditDriverTests extends TestBase {

    @Test(groups = {"regression", "smoke"})
    public void EditDriverTest() throws InterruptedException {
        // 1. Navigate to Elar App
        System.out.println("1. Navigate to Elar App");
        driver.get(ConfigReader.getProperty("elarappUrl"));

        // 2. Login
        System.out.println("2. Login");
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        // 3. Click on Driver tab
        System.out.println("3. Click on Driver tab");
        DriverHomePage driverHomePage = new DriverHomePage();
        driverHomePage.driverTabButton.click();

        // 4. Click on Add Driver button
        System.out.println("4. Click on Add Driver button");
        driverHomePage.addDriverButton.click();

        // 5. Fill data for driver
        System.out.println("5. Fill data for driver");
        CreateDriverPage createDriverPage = new CreateDriverPage();
        createDriverPage.isStaff.click();
        createDriverPage.fullNameInput.sendKeys("josh king");
        createDriverPage.driverLicenseExp.click();
        createDriverPage.driverLicenseExp.sendKeys("07232028");
        createDriverPage.medicalCertificationExp.click();
        createDriverPage.medicalCertificationExp.sendKeys("07232028");

        // 6. Click on create new driver button
        System.out.println("6. Click on create new driver button");
        createDriverPage.createNwBtn.click();

        // 7. Go to edit page and click on edit button
        System.out.println("7. Go to edit page and click on edit button");
        createDriverPage.goToEditBtn.click();
        EditDriverPage editDriverPage = new EditDriverPage();
        editDriverPage.editBtn.click();

        // 8. Edit driver name and click update button
        System.out.println("8. Edit driver name and click update button");
        BrowserUtils.scrollUpOnPage();
        editDriverPage.fullNameInput.click();
        editDriverPage.fullNameInput.sendKeys(Keys.CONTROL + "a");
        editDriverPage.fullNameInput.sendKeys(Keys.BACK_SPACE);
        editDriverPage.fullNameInput.sendKeys("Solomiia Gonzalez");
        editDriverPage.updateBtn.click();
        String driverName = editDriverPage.fullNameInput.getAttribute("value");
        String driverId = editDriverPage.driverId.getAttribute("value");

        // 9. Go to search page and search for driver
        System.out.println("9. Go to search page and search for driver");
        BrowserUtils.scrollUpOnPage();
        editDriverPage.backToListBtn.click();
        driverHomePage.searchResultName.sendKeys(driverId + Keys.ENTER);

        // 10. Validate updated driver name
        System.out.println("10. Validate updated driver name");
        Thread.sleep(2000);
        String actualDriverName = driverHomePage.driverNameInSearchResult.getText();
        Assert.assertEquals(actualDriverName, driverName);
    }

    @Test(groups = {"regression", "smoke"})
    public void EditDriverTestNegative() throws InterruptedException {
        // 1. Navigate to Elar App
        System.out.println("1. Navigate to Elar App");
        driver.get(ConfigReader.getProperty("elarappUrl"));

        // 2. Login
        System.out.println("2. Login");
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        // 3. Click on Driver tab
        System.out.println("3. Click on Driver tab");
        DriverHomePage driverHomePage = new DriverHomePage();
        driverHomePage.driverTabButton.click();

        // 4. Click on Add Driver button
        System.out.println("4. Click on Add Driver button");
        driverHomePage.addDriverButton.click();

        // 5. Fill data for driver
        System.out.println("5. Fill data for driver");
        CreateDriverPage createDriverPage = new CreateDriverPage();
        createDriverPage.isStaff.click();
        createDriverPage.fullNameInput.sendKeys("josh king");
        createDriverPage.driverLicenseExp.click();
        createDriverPage.driverLicenseExp.sendKeys("07232028");
        createDriverPage.medicalCertificationExp.click();
        createDriverPage.medicalCertificationExp.sendKeys("07232028");

        // 6. Click on create new driver button
        System.out.println("6. Click on create new driver button");
        createDriverPage.createNwBtn.click();

        // 7. Go to edit page and click on edit button
        System.out.println("7. Go to edit page and click on edit button");
        createDriverPage.goToEditBtn.click();
        EditDriverPage editDriverPage = new EditDriverPage();
        editDriverPage.editBtn.click();

        // 8. Edit driver name and click update button
        System.out.println("8. Edit driver name and click update button");
        BrowserUtils.scrollUpOnPage();
        editDriverPage.fullNameInput.click();
        editDriverPage.fullNameInput.sendKeys(Keys.CONTROL + "a");
        editDriverPage.fullNameInput.sendKeys(Keys.BACK_SPACE);
        editDriverPage.fullNameInput.sendKeys("Solomiia@ Gonzalez");
        editDriverPage.updateBtn.click();

        // 9. Validate error message
        System.out.println("9. Validate error message");
        String expectedResult = "Input must contain only alphanumeric and specific punctuation characters";
        String actualResult = editDriverPage.nameErrorMessage.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
