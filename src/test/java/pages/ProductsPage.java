package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//div[text()='%s']/ancestor::div[@data-test='inventory-item-description']//button[text()='Add to cart']";
    public static final String REMOVE_FROM_CART_PATTERN = "//div[text()='%s']/ancestor::div[@data-test='inventory-item-description']//button[text()='Remove']";
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By cartLinkBadge = By.cssSelector("span.shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean titleIsVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public void addToCart(final String productName) {
        By addToCartBtn = By.xpath(String.format(ADD_TO_CART_PATTERN, productName));
        driver.findElement(addToCartBtn).click();
    }

    public boolean removeBtnIsVisible(final String productName) {
        By removeFromCartBtn = By.xpath(REMOVE_FROM_CART_PATTERN.formatted(productName));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartBtn)).isDisplayed();
    }

    public boolean addToCartBtnIsVisible(final String productName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(productName));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).isDisplayed();
    }

    public void removeFromCart(final String productName) {
        By removeFromCartBtn = By.xpath(REMOVE_FROM_CART_PATTERN.formatted(productName));
        driver.findElement(removeFromCartBtn).click();
    }

    public int counterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkBadge)).isDisplayed();
        return Integer.parseInt(driver.findElement(cartLinkBadge).getText());
    }

    public void waitForCartBadgeToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartLinkBadge));
    }

    public String counterColour() {
        return driver.findElement(cartLinkBadge).getCssValue("background-color");
    }
}
