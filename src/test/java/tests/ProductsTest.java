package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.ABOUT_URL;
import static pages.BasePage.ITEM_NAME;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    @Epic("UI Automation")
    @Feature("Products")
    @Story("Добавление товаров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkGoodsAdded() {
        Allure.step("Авторизоваться и добавить товары в корзину", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            assertTrue(productsPage.titleIsVisible());
            productsPage.addToCart(ITEM_NAME);
            for (String product : productsList) {
                productsPage.addToCart(product);
            }
        });
        Allure.step("Проверить кнопки Remove, счетчик и его цвет", () -> {
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
        });
    }

    @Epic("UI Automation")
    @Feature("Products")
    @Story("Удаление товаров из корзины")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkRemoveBtn() {
        Allure.step("Авторизоваться и добавить товары", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            assertTrue(productsPage.titleIsVisible());
            for (String product : productsList) {
                productsPage.addToCart(product);
            }
        });
        Allure.step("Удалить товары и проверить очистку бейджа", () -> {
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
        });
    }

    @Epic("UI Automation")
    @Feature("Products")
    @Story("Переход в корзину")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkCartLink() {
        Allure.step("Добавить товар {ITEM_NAME} и перейти в корзину", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openCart();
        });
        Allure.step("Проверить заголовок страницы корзины", () ->
                assertEquals(cartPage.getTitle(), "Your Cart")
        );
    }

    @Epic("UI Automation")
    @Feature("Products")
    @Story("Сброс состояния приложения")
    @Severity(SeverityLevel.MINOR)
    @Owner("Kotikova Ann")
    @Test
    public void checkResetAppState() {
        Allure.step("Добавить товар и выполнить Reset App State", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.addToCart(ITEM_NAME);
            assertEquals(productsPage.counterValue(), 1);
            productsPage.navigationPanel.openMenu();
            productsPage.navigationPanel.resetAppState();
        });
        Allure.step("Проверить, что корзина очищена", () -> {
            productsPage.waitForCartBadgeToDisappear();
            assertFalse(productsPage.removeBtnIsVisible(ITEM_NAME));
        });
    }

    @Epic("UI Automation")
    @Feature("Products")
    @Story("Переход на страницу About")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("Kotikova Ann")
    @Test
    public void checkSwitchToAboutPage() {
        Allure.step("Авторизоваться и открыть страницу About через меню", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.navigationPanel.openMenu();
            productsPage.navigationPanel.openAboutPage();
        });
        Allure.step("Проверить URL страницы About", () ->
                assertTrue(productsPage.isCurrentUrl(ABOUT_URL))
        );
    }
}
