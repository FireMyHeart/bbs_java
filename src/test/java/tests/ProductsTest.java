package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.ABOUT_URL;
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
        productsPage.waitForCartBadgeToDisappear();
    }

    @Test
    public void checkCartLink() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), "1");
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test
    public void checkResetAppState() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), "1");
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.resetAppState();
        productsPage.waitForCartBadgeToDisappear();
        assertFalse(productsPage.removeBtnIsVisible(ITEM_NAME));
        /*
        вот из-за этого тест падает. Я думаю, что это баг. Потому что раз состояние корзины сбрасывается и в корзине
        пусто становится, то и кнопка Remove должна замениться Add to cart, а это происходит только после рефреша страницы.
        Надо было заложить рефреш страницы при нажатии этой кнопки
         */
    }

    @Test
    public void checkSwitchToAboutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.openAboutPage();
        assertTrue(productsPage.isCurrentUrl(ABOUT_URL));
    }
}
