package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends BasePage {
    public static final double TAX_RATE = 0.08;
    private final By title = By.cssSelector("span.title");
    private final By itemName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By totalPrice = By.cssSelector("div[data-test='subtotal-label']");
    private final By tax = By.cssSelector("div[data-test='tax-label']");
    private final By total = By.cssSelector("div[data-test='total-label']");
    private final By finishBtn = By.cssSelector("button[id='finish']");
    public static final String ITEM_PRICE =
            "//div[@class='cart_item_label' and .//div[@data-test='inventory-item-name' and text()='%s']]//div[@data-test='inventory-item-price']";

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы Overview")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Получить названия товаров на странице Overview")
    public ArrayList<String> getProductsNames() {
        List<WebElement> allProducts = driver.findElements(itemName);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Получить цену товара на Overview: {productName}")
    public double getItemPrice(final String productName) {
        By itemPrice = By.xpath(ITEM_PRICE.formatted(productName));
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice)).getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    @Step("Получить Item total на Overview")
    public double getTotalPrice() {
        String totalPriceText = driver.findElement(totalPrice).getText();
        return Double.parseDouble(totalPriceText.replace("Item total: $", "").trim());
    }

    @Step("Получить Tax на Overview")
    public double getTax() {
        String taxText = driver.findElement(tax).getText();
        return Double.parseDouble(taxText.replace("Tax: $", "").trim());
    }

    @Step("Получить Total на Overview")
    public double getTotal() {
        String totalText = driver.findElement(total).getText();
        return Double.parseDouble(totalText.replace("Total: $", "").trim());
    }

    @Step("Нажать кнопку Finish")
    public void clickFinishBtn() {
        driver.findElement(finishBtn).click();
    }
}
