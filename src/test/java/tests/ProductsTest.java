package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.ITEM_NAME;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.titleIsVisible());
        productsPage.addToCart(ITEM_NAME);
        assertTrue(productsPage.removeBtnIsVisible(ITEM_NAME));
        assertEquals(productsPage.counterValue(), "1");
        assertEquals(productsPage.counterColour(), "rgba(226, 35, 26, 1)");
    }

    @Test
    public void checkCartLink() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), "1");
        productsPage.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
