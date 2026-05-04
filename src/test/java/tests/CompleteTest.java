package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCheckoutData;

@Epic("UI Automation")
@Feature("Complete")
@Owner("Kotikova Ann")
public class CompleteTest extends BaseTest {
    @Story("Проверка страницы завершения заказа")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void checkCompletePage() {
        Allure.step("Открыть сайт и авторизоваться", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
        });
        Allure.step("Оформить заказ до страницы Complete", () -> {
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
            cartPage.clickCheckoutBtn();
            checkoutPage.fillCheckoutForm(withCheckoutData());
            checkoutPage.clickContinueBtn();
            overviewPage.clickFinishBtn();
        });
        Allure.step("Проверить содержимое страницы Complete", () -> {
            assertEquals(completePage.getCompleteHeader(), "Thank you for your order!");
            assertEquals(completePage.getCompleteText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
            assertTrue(completePage.isDoneImageVisible(), "Изображение с галочкой не отображается");
            assertTrue(completePage.isCartLinkBadgeInvisible(), "Кол-во товаров в корзине не исчезает");
        });
    }

    @Story("Возврат на страницу товаров после заказа")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void checkSwitchToProductsPage() {
        Allure.step("Дойти до страницы Complete", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
            cartPage.clickCheckoutBtn();
            checkoutPage.fillCheckoutForm(withCheckoutData());
            checkoutPage.clickContinueBtn();
            overviewPage.clickFinishBtn();
        });
        Allure.step("Вернуться на страницу Products", () -> {
            completePage.clickBackHomeBtn();
            assertEquals(productsPage.getTitle(), "Products");
        });
    }
}
