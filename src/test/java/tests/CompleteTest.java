package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCheckoutData;

public class CompleteTest extends BaseTest {
    @Test
    public void checkCompletePage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        cartPage.clickCheckoutBtn();
        checkoutPage.fillCheckoutForm(withCheckoutData());
        checkoutPage.clickContinueBtn();
        overviewPage.clickFinishBtn();
        assertEquals(completePage.getCompleteHeader(), "Thank you for your order!");
        assertEquals(completePage.getCompleteText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        assertTrue(completePage.isDoneImageVisible(), "Изображение с галочкой не отображается");
        assertTrue(completePage.isCartLinkBadgeInvisible(), "Кол-во товаров в корзине не исчезает");
    }

    @Test
    public void checkSwitchToProductsPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        cartPage.clickCheckoutBtn();
        checkoutPage.fillCheckoutForm(withCheckoutData());
        checkoutPage.clickContinueBtn();
        overviewPage.clickFinishBtn();
        completePage.clickBackHomeBtn();
        assertEquals(productsPage.getTitle(), "Products");
    }
}
