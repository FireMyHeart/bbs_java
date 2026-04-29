package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By title = By.cssSelector("span.title");
    private final By items = By.cssSelector("div.cart_item");
    private final By itemName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By checkoutBtn = By.cssSelector("button[id='checkout']");
    public static final String ITEM_PRICE =
            "//div[@class='cart_item_label' and .//div[@data-test='inventory-item-name' and text()='%s']]//div[@data-test='inventory-item-price']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить заголовок страницы корзины")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Проверить, что блок товаров в корзине отображается")
    public boolean itemsBlockIsVisible() {
        return driver.findElement(items).isDisplayed();
    }

    @Step("Получить количество товаров в корзине")
    public int itemsCount() {
        return driver.findElements(itemName).size();
    }

    @Step("Получить название первого товара в корзине")
    public String firstItemName() {
        return driver.findElements(itemName).getFirst().getText();
    }

    @Step("Получить названия всех товаров в корзине")
    public ArrayList<String> getProductsNames() {
        List<WebElement> allProducts = driver.findElements(itemName);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Получить цену товара в корзине: {productName}")
    public double getItemPrice(final String productName) {
        By itemPrice = By.xpath(ITEM_PRICE.formatted(productName));
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice)).getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    @Step("Нажать кнопку Checkout")
    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }

    @Step("Перейти на страницу Checkout и заполнить данные пользователя")
    public void checkoutWithUserData(CheckoutPage checkoutPage, User user) {
        clickCheckoutBtn();
        checkoutPage.fillCheckoutForm(user);
    }
}
