package tests;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    @Test
    public void checkProductIsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "Название страницы не совпало");
        assertTrue(cartPage.itemsBlockIsVisible());
        assertEquals(cartPage.itemsCount(), 1, "кол-во товаров в корзине не равно 1");
        assertEquals(cartPage.firstItemName(), ITEM_NAME, "Название товара отличается");
    }

    @Test
    public void checkProductsNames() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        assertEquals(productsPage.counterValue(), 3);
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        List<String> actualProducts = cartPage.getProductsNames();
        assertEquals(actualProducts.size(), productsList.size());
        assertTrue(actualProducts.containsAll(productsList));
    }

    @Test
    public void checkReturnToAllProductsPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.navigationPanel.openCart();
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.openProductsPage();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkProductsPricesInCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        Map<String, Double> expectedProductsPrices = new HashMap<>();
        for (String product : productsList) {
            double productPrice = productsPage.getItemPrice(product);
            expectedProductsPrices.put(product, productPrice);
            productsPage.addToCart(product);
        }

        assertEquals(productsPage.counterValue(), 3);
        productsPage.navigationPanel.openCart();

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
    }

    @Test
    public void checkSwitchToCheckoutPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        cartPage.clickCheckoutBtn();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
