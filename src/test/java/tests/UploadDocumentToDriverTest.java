package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateDriverPage;
import pages.DriverHomePage;
import pages.EditDriverPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public class UploadDocumentToDriverTest extends TestBase {

    String path = System.getProperty("user.dir") + "/src/test/resources/testdata/Screenshot.png";
    String pathError = System.getProperty("user.dir") + "/src/test/resources/testdata/video.MOV.";

    @Test(groups = {"regression", "smoke"})
    public void uploadDocumentDownloadToDriverTest() throws AWTException, InterruptedException {
        commonSteps();
        // 10. Download file and validate it is downloaded
        System.out.println("10. Download file and validate it is downloaded");
        driver.findElement(By.xpath("//button[@title='Download']")).click();
        Set<String> windowIds = driver.getWindowHandles();
        Assert.assertEquals(windowIds.size(), 2);
    }

    @Test(groups = {"regression", "smoke"})
    public void UploadDocumentReviewToDriverTest() throws AWTException, InterruptedException {
        commonSteps();
        // 10. Preview file and validate it is previewed
        System.out.println("10. Preview file and validate it is Previewed");
        driver.findElement(By.xpath("//button[@title='Preview']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Screenshot.png']")).isDisplayed());
    }

    @Test(groups = {"regression"})
    public void UploadDocumentDeleteToDriverTest() throws AWTException, InterruptedException {
        commonSteps();
        // 10. Delete file and validate it is deleted
        System.out.println("10. Delete file and validate it is deleted");
        driver.findElement(By.xpath("//button[@title='Delete']")).click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();
        Thread.sleep(1000);
        String numberOfDocs = driver.findElement(By.xpath("//h6[text()='Medical cert document']/following-sibling::p")).getText();
        Assert.assertEquals(numberOfDocs, "0 out of 10");
    }

    @Test
    public void uploadDocumentBigSizeDriverTestError() throws AWTException, InterruptedException {
        createDriverAndClickUploadDocument();
        // 9. Upload big file
        System.out.println("9. Upload big file");
        String bigFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/capitanamerica.mkv";
        WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
        upload.sendKeys(bigFilePath);
        Thread.sleep(3000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(3000);
        String expectedErrorMessage = "Error occurred during file upload";
        String actualErrorMessage = driver.findElement(By.xpath("")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    public void commonSteps() throws InterruptedException, AWTException {
        createDriverAndClickUploadDocument();
        // 9. Upload file
        System.out.println("9. Upload file");
        WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
        upload.sendKeys(path);
        Thread.sleep(3000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(3000);
    }

    private void createDriverAndClickUploadDocument() {
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

        //8. click on Medical cert Document "Add Document"
        System.out.println("8. click on Medical cert Document Add Document");
        EditDriverPage editDriverPage = new EditDriverPage();
        editDriverPage.addMCDocument.click();
    }
}