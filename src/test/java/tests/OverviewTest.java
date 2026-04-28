package tests;

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
    @Test
    public void checkOverviewPriceSummary() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        Map<String, Double> expectedProductsPrices = new HashMap<>();
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
    }

    @Test
    public void checkSwitchToCompletePage() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(ITEM_NAME);
        assertEquals(productsPage.counterValue(), 1);
        productsPage.navigationPanel.openCart();
        cartPage.clickCheckoutBtn();
        checkoutPage.fillCheckoutForm(withCheckoutData());
        checkoutPage.clickContinueBtn();
        overviewPage.clickFinishBtn();
        assertEquals(completePage.getTitle(), "Checkout: Complete!");
    }
}
