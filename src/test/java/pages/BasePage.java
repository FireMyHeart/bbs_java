package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.Objects;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    public static final String ITEM_NAME = "Sauce Labs Fleece Jacket";
    public static final String ABOUT_URL = PropertyReader.getProperty("saucedemo.about_url");
    public final NavigationPanel navigationPanel;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.navigationPanel = new NavigationPanel(driver, wait);
    }

    public boolean isCurrentUrl(String url) {
        return Objects.equals(driver.getCurrentUrl(), url);
    }
}
