package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.CheckoutUser;

public class CheckoutPage extends BasePage {
    private final By title = By.cssSelector("span.title");
    private final By firstnameField = By.cssSelector("input[id='first-name']");
    private final By lastnameField = By.cssSelector("input[id='last-name']");
    private final By zipCodeField = By.cssSelector("input[id='postal-code']");
    private final By continueBtn = By.cssSelector("input[id='continue']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы Checkout")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Заполнить checkout-форму")
    public void fillCheckoutForm(CheckoutUser user) {
        Allure.step("Заполнить First Name: " + user.getFirstname(), () ->
                driver.findElement(firstnameField).sendKeys(user.getFirstname())
        );
        Allure.step("Заполнить Last Name: " + user.getLastname(), () ->
                driver.findElement(lastnameField).sendKeys(user.getLastname())
        );
        Allure.step("Заполнить Zip/Postal Code: " + user.getZipcode(), () ->
                driver.findElement(zipCodeField).sendKeys(user.getZipcode())
        );
    }

    @Step("Нажать кнопку Continue")
    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }
}
