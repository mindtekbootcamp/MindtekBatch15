package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.Driver;

import java.util.List;

public class DriverHomePage {

    WebDriver driver;

    public DriverHomePage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/drivers/list']")
    public WebElement driverTabButton;

    @FindBy(xpath = "//div[@role='combobox']")
    public WebElement paginationButton;

    @FindBy(xpath = "//button[@title='Go to next page']")
    public WebElement goToNextPageButton;

    public void selectNumberOfDriversPerPage(int number){
        driver.findElement(By.xpath("//li[@data-value='"+number+"']")).click();
    }

    public int getTotalNumberOfDrivers() {
        BrowserUtils.waitElementToPresent(By.xpath("//p[@class='MuiTablePagination-displayedRows css-htselv']"));
        String numberOfDrivers = driver.findElement(By.xpath("//p[@class='MuiTablePagination-displayedRows css-htselv']")).getText().split("of ")[1]; // 1–25 of 92 -> ["1-25 ", "92"] - > "92"
        return Integer.parseInt(numberOfDrivers);
    }

    public String getLastRowIndexFromTable(){
        BrowserUtils.scrollDownOnTable(driver.findElement(By.xpath("//div[@class='MuiDataGrid-virtualScroller css-1pzb349']")));
        BrowserUtils.waitElementToPresent(By.xpath("//div[@role='row' and @data-rowindex]"));
        List<WebElement> numberOfRows = driver.findElements(By.xpath("//div[@role='row' and @data-rowindex]"));
        return numberOfRows.getLast().getAttribute("data-rowindex");
    }

}
