package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.TestBase;

public class ValidateLogin extends TestBase {

    @Test
    public void verifyLogin(){
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys(ConfigReader.getProperty("elarUsername"));
        driver.findElement(By.id("login-password")).sendKeys(ConfigReader.getProperty("elarPassword"));
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();

        String actualResult = driver.findElement(By.xpath("//a[@href='/users/list']")).getText();
        String expectedResult = "Users";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void verifyInvalidLogin() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys("abc@mindtek.com");
        driver.findElement(By.id("login-password")).sendKeys("004H78");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();

        Thread.sleep(1000);
        String expectedResult = "Invalid username or password";
        String actualResult = driver.findElement(By.xpath("//div[@role='status']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test
    public void verifyInvalidLoginPass() throws InterruptedException {
        driver.get(ConfigReader.getProperty("elarappUrl"));
        driver.findElement(By.id("login-username")).sendKeys("student@mindtek.com");
        driver.findElement(By.id("login-password")).sendKeys("159963");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/button")).click();

        Thread.sleep(1000);
        String expectedResult = "Invalid username or password";
        String actualResult = driver.findElement(By.xpath("//div[@role='status']")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
