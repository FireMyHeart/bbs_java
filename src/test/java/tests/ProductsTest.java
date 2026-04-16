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
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        assertTrue(productsPage.removeBtnIsVisible(ITEM_NAME));
        for (String product : productsList) {
            assertTrue(productsPage.removeBtnIsVisible(product));
        }
        assertEquals(productsPage.counterValue(), "4");
        assertEquals(productsPage.counterColour(), "rgba(226, 35, 26, 1)");
    }

    @Test
    public void checkRemoveBtn() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.titleIsVisible());
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        for (String product : productsList) {
            assertTrue(productsPage.removeBtnIsVisible(product));
        }
        assertEquals(productsPage.counterValue(), "3");
        for (String product : productsList) {
            productsPage.removeFromCart(product);
        }
        for (String product : productsList) {
            assertTrue(productsPage.addToCartBtnIsVisible(product));
        }
        assertTrue(productsPage.CartBadgeInvisibility());
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
