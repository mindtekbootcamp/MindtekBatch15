package tests;

import org.testng.annotations.Test;
import pages.DriverHomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestBase;

public class SortingDriversFunctionalityTest extends TestBase {

    @Test
    public void sortingDriversByDefaultLowToHighTest() {

        // 1-Navigate to elar app
        System.out.println("navigate to elar app");
        driver.get(ConfigReader.getProperty("elarappUrl"));

        //2- Login
        System.out.println("login");
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        //3- Click on driver tab
        System.out.println("Click on Driver Tab");
        DriverHomePage driverHomePage = new DriverHomePage();
        driverHomePage.driverTabButton.click();

        //4-Click on sorting Button
        System.out.println("Click on sorting button");
        driverHomePage.sortingBtn.click();

        //5-Validate Low to High by Id is selected by default
        System.out.println("Validate Low to High by Id is selected by default");
        driverHomePage.validateLowToHighIsSelectedByDefault();

        //6-Validate IDs are sorted by Low to High
        System.out.println("Validate IDs are sorted by Low to High");
        driverHomePage.validateSearchResultIdsSorted("low to high");
    }

    @Test
    public void sortingDriversHighToLowTest() throws InterruptedException {

        // 1-Navigate to elar app
        System.out.println("navigate to elar app");
        driver.get(ConfigReader.getProperty("elarappUrl"));

        //2- Login
        System.out.println("login");
        LoginPage loginPage = new LoginPage();
        loginPage.login();

        //3- Click on driver tab
        System.out.println("Click on Driver Tab");
        DriverHomePage driverHomePage = new DriverHomePage();
        driverHomePage.driverTabButton.click();

        //4-Click on sorting Button and select high to low
        System.out.println("Click on sorting button");
        driverHomePage.sortingBtn.click();
        driverHomePage.sortingBtnHighToLow.click();

        //6-Validate IDs are sorted by High to Low
        System.out.println("Validate IDs are sorted by High To Low");
        Thread.sleep(2000);
        driverHomePage.validateSearchResultIdsSorted("high to low");
    }
}