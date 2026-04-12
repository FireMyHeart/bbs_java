package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.titleIsVisible());
        productsPage.addToCart();
        assertTrue(productsPage.removeBtnIsVisible());
        assertEquals(productsPage.counterValue(), "1");
        assertEquals(productsPage.counterColour(), "rgba(0, 0, 0, 0)");
    }

    @Test
    public void checkCartIsOpen() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart();
        assertEquals(productsPage.counterValue(), "1");
        productsPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
