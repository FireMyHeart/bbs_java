package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart();
        assertEquals(productsPage.counterValue(), "1");
        productsPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "Название страницы не совпало");
        assertTrue(cartPage.itemsBlockIsVisible());
        assertEquals(cartPage.itemsCount(), 1, "кол-во товаров в корзине не равно 1");
        assertEquals(cartPage.firstItemName(), BasePage.SAUCE_LABS_BACKPACK, "Название товара отличается");
    }
}
