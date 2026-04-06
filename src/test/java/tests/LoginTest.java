package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_userjhksdckczc");
        driver.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");
        driver.findElement(By.id("user-name")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        String title = driver.findElement(By.cssSelector("[data-test='title']")).getText();
        assertEquals(title, "Products");
    }

    @Test
    public void checkIncorrectLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_userjhksdckczc");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        boolean isErrorMsgDisplayed = driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
        assertTrue(isErrorMsgDisplayed, "Сообщение об ошибке не появилось");
        String text = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertEquals(text, "Epic sadface: Username and password do not match any user in this service");
    }
}
