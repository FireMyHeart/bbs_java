package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class CheckoutPage extends BasePage {
    private final By title = By.cssSelector("span.title");
    private final By firstnameField = By.cssSelector("input[id='first-name']");
    private final By lastnameField = By.cssSelector("input[id='last-name']");
    private final By zipCodeField = By.cssSelector("input[id='postal-code']");
    private final By continueBtn = By.cssSelector("input[id='continue']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void fillCheckoutForm(User user) {
        driver.findElement(firstnameField).sendKeys(user.getFirstname());
        driver.findElement(lastnameField).sendKeys(user.getLastname());
        driver.findElement(zipCodeField).sendKeys(user.getZipcode());
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }
}
