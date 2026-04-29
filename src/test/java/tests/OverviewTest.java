package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.BasePage.ITEM_NAME;
import static pages.OverviewPage.TAX_RATE;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCheckoutData;

public class OverviewTest extends BaseTest {
    @Epic("UI Automation")
    @Feature("Overview")
    @Story("Проверка итоговой суммы заказа")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkOverviewPriceSummary() {
        Map<String, Double> expectedProductsPrices = new HashMap<>();
        Allure.step("Подготовить корзину и перейти на страницу Overview", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            for (String product : productsList) {
                double productPrice = productsPage.getItemPrice(product);
                expectedProductsPrices.put(product, productPrice);
                productsPage.addToCart(product);
            }
            assertEquals(productsPage.counterValue(), 3);
            productsPage.navigationPanel.openCart();
            cartPage.clickCheckoutBtn();
            checkoutPage.fillCheckoutForm(withCheckoutData());
            checkoutPage.clickContinueBtn();
        });
        Allure.step("Проверить цены товаров и итоговые суммы", () -> {
            List<String> actualProducts = overviewPage.getProductsNames();
            assertEquals(actualProducts.size(), productsList.size());
            assertTrue(actualProducts.containsAll(productsList));

            double overviewItemsTotal = 0.0;
            for (String product : productsList) {
                double actualOverviewPrice = overviewPage.getItemPrice(product);
                overviewItemsTotal += actualOverviewPrice;
                assertEquals(
                        actualOverviewPrice,
                        expectedProductsPrices.get(product),
                        0.001,
                        "Цена товара в overview отличается для: " + product
                );
            }

            double actualSubtotal = overviewPage.getTotalPrice();
            assertEquals(
                    actualSubtotal,
                    overviewItemsTotal,
                    0.001,
                    "Сумма цен товаров не совпадает с Item total на странице overview"
            );

            double expectedTax = Math.round(actualSubtotal * TAX_RATE * 100.0) / 100.0;
            double actualTax = overviewPage.getTax();
            assertEquals(
                    actualTax,
                    expectedTax,
                    0.001,
                    "Tax не равен 8% от Item total на странице overview"
            );

            double expectedTotal = actualSubtotal + actualTax;
            assertEquals(
                    overviewPage.getTotal(),
                    expectedTotal,
                    0.001,
                    "Total не равен сумме Item total и Tax на странице overview"
            );
        });
    }

    @Epic("UI Automation")
    @Feature("Overview")
    @Story("Завершение заказа и переход на Complete")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkSwitchToCompletePage() {
        Allure.step("Дойти до страницы Overview и нажать Finish", () -> {
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
        Allure.step("Проверить заголовок страницы Complete", () ->
                assertEquals(completePage.getTitle(), "Checkout: Complete!")
        );
    }
}
