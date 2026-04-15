package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static pages.BasePage.ITEM_NAME;

public class CartTest extends BaseTest {
    @Test
    public void checkProductIsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), "1");
        productsPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "Название страницы не совпало");
        assertTrue(cartPage.itemsBlockIsVisible());
        assertEquals(cartPage.itemsCount(), 1, "кол-во товаров в корзине не равно 1");
        assertEquals(cartPage.firstItemName(), ITEM_NAME, "Название товара отличается");
    }

    @Test
    public void checkProductsNames() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        assertEquals(productsPage.counterValue(), "3");
        productsPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        List<String> actualProducts = cartPage.getProductsNames();
        assertEquals(actualProducts.size(), productsList.size());
        assertTrue(actualProducts.containsAll(productsList));
    }
}
