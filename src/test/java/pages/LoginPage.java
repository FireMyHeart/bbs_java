package pages;

import io.qameta.allure.Step;
import io.qameta.allure.Allure;
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

    @Step("Перейти на страницу saucedemo.com")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Перейти на страницу: {url}")
    public void open(final String url) {
        driver.get(BASE_URL + url);
    }

    @Step("Пройти авторизацию")
    public void login(User user) {
        Allure.step("Заполнить поле Username: " + user.getLogin(), () ->
                driver.findElement(userField).sendKeys(user.getLogin())
        );
        Allure.step("Заполнить поле Password: " + user.getPassword(), () ->
                driver.findElement(passField).sendKeys(user.getPassword())
        );
        Allure.step("Нажать кнопку Login", () ->
                driver.findElement(submitBtn).click()
        );
    }

    @Step("Проверить, что сообщение об ошибке отображается")
    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMsgText() {
        return driver.findElement(errorMsg).getText();
    }

    @Step("Проверить, что форма логина отображается")
    public boolean isLoginFormVisible() {
        return driver.findElement(userField).isDisplayed() && driver.findElement(submitBtn).isDisplayed();
    }
}
