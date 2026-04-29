package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    @Epic("UI Automation")
    @Feature("Cart")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkProductIsAdded() {
        Allure.step("Авторизоваться, добавить товар и открыть корзину", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
        });
        Allure.step("Проверить состав корзины", () -> {
            assertEquals(cartPage.getTitle(), "Your Cart", "Название страницы не совпало");
            assertTrue(cartPage.itemsBlockIsVisible());
            assertEquals(cartPage.itemsCount(), 1, "кол-во товаров в корзине не равно 1");
            assertEquals(cartPage.firstItemName(), ITEM_NAME, "Название товара отличается");
        });
    }

    @Epic("UI Automation")
    @Feature("Cart")
    @Story("Отображение списка товаров в корзине")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkProductsNames() {
        Allure.step("Добавить несколько товаров и перейти в корзину", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            for (String product : productsList) {
                productsPage.addToCart(product);
            }
            assertEquals(productsPage.counterValue(), 3);
            productsPage.navigationPanel.openCart();
        });
        Allure.step("Проверить список товаров в корзине", () -> {
            assertEquals(cartPage.getTitle(), "Your Cart");
            assertFalse(cartPage.getProductsNames().isEmpty());
            assertEquals(cartPage.getProductsNames().size(), 3);
            List<String> actualProducts = cartPage.getProductsNames();
            assertEquals(actualProducts.size(), productsList.size());
            assertTrue(actualProducts.containsAll(productsList));
        });
    }

    @Epic("UI Automation")
    @Feature("Cart")
    @Story("Возврат на страницу товаров из меню")
    @Severity(SeverityLevel.MINOR)
    @Owner("Kotikova Ann")
    @Test
    public void checkReturnToAllProductsPage() {
        Allure.step("Открыть корзину и перейти на страницу товаров", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.navigationPanel.openCart();
            productsPage.navigationPanel.openMenu();
            productsPage.navigationPanel.openProductsPage();
        });
        Allure.step("Проверить заголовок страницы Products", () ->
                assertEquals(productsPage.getTitle(), "Products")
        );
    }

    @Epic("UI Automation")
    @Feature("Cart")
    @Story("Проверка цен товаров в корзине")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkProductsPricesInCart() {
        Map<String, Double> expectedProductsPrices = new HashMap<>();
        Allure.step("Собрать цены на странице Products и добавить товары в корзину", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            for (String product : productsList) {
                double productPrice = productsPage.getItemPrice(product);
                expectedProductsPrices.put(product, productPrice);
                productsPage.addToCart(product);
            }
            assertEquals(productsPage.counterValue(), 3);
            productsPage.navigationPanel.openCart();
        });
        Allure.step("Сравнить цены в корзине с ожидаемыми", () -> {
            List<String> actualProducts = cartPage.getProductsNames();
            assertEquals(actualProducts.size(), productsList.size());
            assertTrue(actualProducts.containsAll(productsList));
            for (String product : productsList) {
                assertEquals(
                        cartPage.getItemPrice(product),
                        expectedProductsPrices.get(product),
                        0.001,
                        "Цена товара в корзине отличается для: " + product
                );
            }
        });
    }

    @Epic("UI Automation")
    @Feature("Cart")
    @Story("Переход на страницу Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkSwitchToCheckoutPage() {
        Allure.step("Добавить товар и перейти к оформлению", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
            cartPage.clickCheckoutBtn();
        });
        Allure.step("Проверить заголовок страницы Checkout", () ->
                assertEquals(checkoutPage.getTitle(), "Checkout: Your Information")
        );
    }
}
