package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DriverHomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestBase;

public class PaginationTests extends TestBase {

    @DataProvider(name = "paginationTestData")
    public Object[][] paginationTestData() {
        return new Object[][]{
                {5}, {10}, {25}, {50}
        };
    }

    @Test(dataProvider = "paginationTestData")
    public void paginationTest(int numberOfDriversPerPage) {
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

        // 4. Select number of pages from pagination button
        System.out.println("4. Select number of pages from pagination button");
        driverHomePage.paginationButton.click();
        driverHomePage.selectNumberOfDriversPerPage(numberOfDriversPerPage);

        // 5. Get number of drivers on page 1 and validate
        System.out.println("5. Get number of drivers on page 1 and validate");
        int numberOfDriversInt = driverHomePage.getTotalNumberOfDrivers();
        int expectedNumberOfDriver = numberOfDriversPerPage;
        if (numberOfDriversInt < numberOfDriversPerPage) expectedNumberOfDriver = numberOfDriversInt;
        String lastRowIndex = driverHomePage.getLastRowIndexFromTable();
        Assert.assertEquals(lastRowIndex, expectedNumberOfDriver - 1 + "");

        // 6. Go to next page if there is more drivers
        System.out.println("6. Go to next page if there is more drivers");
        if (expectedNumberOfDriver < numberOfDriversPerPage) return;
        driverHomePage.goToNextPageButton.click();

        // 7. Get number of drivers on page 2 and validate
        System.out.println("7. Get number of drivers on page 2 and validate");
        numberOfDriversInt -= numberOfDriversPerPage;
        if (numberOfDriversInt < numberOfDriversPerPage) expectedNumberOfDriver = numberOfDriversInt;
        lastRowIndex = driverHomePage.getLastRowIndexFromTable();
        Assert.assertEquals(lastRowIndex, expectedNumberOfDriver - 1 + "");
    }
}
