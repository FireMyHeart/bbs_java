package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By title = By.cssSelector("span.title");
    private final By items = By.cssSelector("div.cart_item");
    private final By itemName = By.cssSelector("div[data-test='inventory-item-name']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public boolean itemsBlockIsVisible() {
        return driver.findElement(items).isDisplayed();
    }

    public int itemsCount() {
        return driver.findElements(itemName).size();
    }

    public String firstItemName() {
        return driver.findElements(itemName).getFirst().getText();
    }
}
