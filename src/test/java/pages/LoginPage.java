package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By userField = By.id("user-name");
    private final By passField = By.xpath("//*[@placeholder='Password']");
    private final By submitBtn = By.cssSelector("[data-test='login-button']");
    private final By errorMsg = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void open(final String url) {
        driver.get(BASE_URL + url);
    }

    public void login(String login, String password) {
        driver.findElement(userField).sendKeys(login);
        driver.findElement(passField).sendKeys(password);
        driver.findElement(submitBtn).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorMsgText() {
        return driver.findElement(errorMsg).getText();
    }
}
