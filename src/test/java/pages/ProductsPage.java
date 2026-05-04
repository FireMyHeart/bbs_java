package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//div[text()='%s']/ancestor::div[@data-test='inventory-item-description']//button[text()='Add to cart']";
    public static final String REMOVE_FROM_CART_PATTERN = "//div[text()='%s']/ancestor::div[@data-test='inventory-item-description']//button[text()='Remove']";
    public static final String ITEM_PRICE = "//div[text()='%s']/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']";
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By cartLinkBadge = By.cssSelector("span.shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы Products")
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    @Step("Проверить, что заголовок страницы Products отображается")
    public boolean titleIsVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Добавить товар в корзину: {productName}")
    public void addToCart(final String productName) {
        By addToCartBtn = By.xpath(String.format(ADD_TO_CART_PATTERN, productName));
        driver.findElement(addToCartBtn).click();
    }

    @Step("Проверить, что кнопка Remove отображается для товара: {productName}")
    public boolean removeBtnIsVisible(final String productName) {
        By removeFromCartBtn = By.xpath(REMOVE_FROM_CART_PATTERN.formatted(productName));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartBtn)).isDisplayed();
    }

    @Step("Проверить, что кнопка Add to cart отображается для товара: {productName}")
    public boolean addToCartBtnIsVisible(final String productName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(productName));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).isDisplayed();
    }

    @Step("Удалить товар из корзины: {productName}")
    public void removeFromCart(final String productName) {
        By removeFromCartBtn = By.xpath(REMOVE_FROM_CART_PATTERN.formatted(productName));
        driver.findElement(removeFromCartBtn).click();
    }

    @Step("Получить цену товара на странице Products: {productName}")
    public double getItemPrice(final String productName) {
        By itemPrice = By.xpath(ITEM_PRICE.formatted(productName));
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice)).getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    @Step("Получить значение счетчика корзины")
    public int counterValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkBadge)).isDisplayed();
        return Integer.parseInt(driver.findElement(cartLinkBadge).getText());
    }

    @Step("Подождать, пока бейдж корзины исчезнет")
    public void waitForCartBadgeToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartLinkBadge));
    }

    @Step("Получить цвет счетчика корзины")
    public String counterColour() {
        return driver.findElement(cartLinkBadge).getCssValue("background-color");
    }
}
