package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPanel {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By cartLink = By.cssSelector("a.shopping_cart_link");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By allItemsPage = By.cssSelector("a[data-test='inventory-sidebar-link']");
    private final By aboutPage = By.cssSelector("a[data-test='about-sidebar-link']");
    private final By logoutPage = By.id("logout_sidebar_link");
    private final By resetAppState = By.id("reset_sidebar_link");

    public NavigationPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Открыть боковое меню")
    public void openMenu() {
        driver.findElement(burgerMenuButton).click();
    }

    @Step("Открыть корзину")
    public void openCart() {
        driver.findElement(cartLink).click();
    }

    @Step("Открыть страницу All Items")
    public void openProductsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(allItemsPage)).isDisplayed();
        driver.findElement(allItemsPage).click();
    }

    @Step("Открыть страницу About")
    public void openAboutPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutPage)).isDisplayed();
        driver.findElement(aboutPage).click();
    }

    @Step("Выйти из аккаунта")
    public void logOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutPage)).isDisplayed();
        driver.findElement(logoutPage).click();
    }

    @Step("Сбросить состояние приложения")
    public void resetAppState() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAppState)).isDisplayed();
        driver.findElement(resetAppState).click();
    }
}
