package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

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

    public void login(User user) {
        driver.findElement(userField).sendKeys(user.getLogin());
        driver.findElement(passField).sendKeys(user.getPassword());
        driver.findElement(submitBtn).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorMsgText() {
        return driver.findElement(errorMsg).getText();
    }

    public boolean isLoginFormVisible() {
        return driver.findElement(userField).isDisplayed() && driver.findElement(submitBtn).isDisplayed();
    }
}
