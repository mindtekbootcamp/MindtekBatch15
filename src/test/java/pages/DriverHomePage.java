package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BrowserUtils;
import utils.Driver;

import java.util.ArrayList;
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

    //Click sorting button
    @FindBy(xpath = "//button[text()='Sorting']")
    public WebElement sortingBtn;

    @FindBy(xpath = "//button[text()='ID Low to High (Default)']")
    public WebElement sortinBtnDefault;

    @FindBy(xpath = "//button[text()='ID High to Low']")
    public WebElement sortingBtnHighToLow;

    @FindBy(xpath = "//div[@role='row']/div[@data-field='driver_number' and @role='cell']/a")
    public List<WebElement> searchResultIDs;

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

    public List<Integer> getSearchResultIds(){
        List<Integer> searchResultIDsInt = new ArrayList<>();
        for(WebElement element: searchResultIDs){
            Integer id=Integer.valueOf(element.getAttribute("href").split("/")[4]);
            searchResultIDsInt.add(id);
        }
        return searchResultIDsInt;
    }

    public void validateSearchResultIdsSorted(String lowToHigh){
        List<Integer> searchResultIDsInt= getSearchResultIds();
        int firstId=searchResultIDsInt.get(0);
        for(int index=1; index<searchResultIDsInt.size(); index++){
            int nextNum = searchResultIDsInt.get(index);
            if(lowToHigh.equalsIgnoreCase("low to high"))
                Assert.assertTrue(firstId<nextNum);
            if(lowToHigh.equalsIgnoreCase("high to low"))
                Assert.assertTrue(firstId>nextNum);
            firstId=nextNum;
        }
    }

    public void validateLowToHighIsSelectedByDefault(){
        Assert.assertTrue(sortinBtnDefault.getAttribute("aria-selected").equals("true"));
    }

}
