package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By addToCartBtn = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@data-test='inventory-item-description']//button[text()='Add to cart']");
    private final By removeFromCartBtn = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@data-test='inventory-item-description']//button[text()='Remove']");
    private final By cartLinkCount = By.cssSelector("a[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean titleIsVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public boolean removeBtnIsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartBtn)).isDisplayed();
    }

    public String counterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkCount)).isDisplayed();
        return driver.findElement(cartLinkCount).getText();
    }

    public void openCart() {
        driver.findElement(cartLinkCount).click();
    }

    public String counterColour() {
        return driver.findElement(cartLinkCount).getCssValue("background-color");
    }
}
