package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCheckoutData;

public class CheckoutTest extends BaseTest {
    @Test
    public void checkSwitchToOverviewPage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        cartPage.clickCheckoutBtn();
        checkoutPage.fillCheckoutForm(withCheckoutData());
        checkoutPage.clickContinueBtn();
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }
}
