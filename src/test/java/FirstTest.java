import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class FirstTest {
    //1 открыть браузер
    //2 зайти на сайт
    @Test
    public void checkLogin() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.id("user-name")).sendKeys("standard_userjhksdckczc");
        browser.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");
        browser.findElement(By.id("user-name")).sendKeys(Keys.BACK_SPACE);
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[data-test='login-button']")).click();
        String title = browser.findElement(By.cssSelector("[data-test='title']")).getText();
        assertEquals(title, "Products");
        browser.quit();
    }
    }
