package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCheckoutData;

@Epic("UI Automation")
@Feature("Checkout")
@Owner("Kotikova Ann")
public class CheckoutTest extends BaseTest {
    @Story("Переход на страницу Overview")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkSwitchToOverviewPage() {
        Allure.step("Дойти до Checkout и заполнить данные", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
            cartPage.clickCheckoutBtn();
            checkoutPage.fillCheckoutForm(withCheckoutData());
            checkoutPage.clickContinueBtn();
        });
        Allure.step("Проверить переход на страницу Overview", () ->
                assertEquals(overviewPage.getTitle(), "Checkout: Overview")
        );
    }
}
