package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.ABOUT_URL;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.titleIsVisible());
        productsPage.addToCart(ITEM_NAME);
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        assertTrue(productsPage.removeBtnIsVisible(ITEM_NAME));
        for (String product : productsList) {
            assertTrue(productsPage.removeBtnIsVisible(product));
        }
        assertEquals(productsPage.counterValue(), 4);
        String counterColor = productsPage.counterColour();
        assertTrue(
                "rgba(226, 35, 26, 1)".equals(counterColor) || "rgb(226, 35, 26)".equals(counterColor),
                "Цвет отличается: " + counterColor
        );
    }

    @Test
    public void checkRemoveBtn() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.titleIsVisible());
        for (String product : productsList) {
            productsPage.addToCart(product);
        }
        for (String product : productsList) {
            assertTrue(productsPage.removeBtnIsVisible(product));
        }
        assertEquals(productsPage.counterValue(), 3);
        for (String product : productsList) {
            productsPage.removeFromCart(product);
        }
        for (String product : productsList) {
            assertTrue(productsPage.addToCartBtnIsVisible(product));
        }
        productsPage.waitForCartBadgeToDisappear();
    }

    @Test
    public void checkCartLink() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test
    public void checkResetAppState() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.resetAppState();
        productsPage.waitForCartBadgeToDisappear();
        assertFalse(productsPage.removeBtnIsVisible(ITEM_NAME));
    }

    @Test
    public void checkSwitchToAboutPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.openAboutPage();
        assertTrue(productsPage.isCurrentUrl(ABOUT_URL));
    }
}
