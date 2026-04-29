package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Epic("UI Automation")
    @Feature("Authentication")
    @Story("Успешный вход пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Kotikova Ann")
    @Test(description = "Авторизация существующего пользователя в системе", priority = 1)
    @TmsLink("bbs_java")
    @Issue("test17")
    public void checkLogin() {
        Allure.step("Открыть страницу логина и войти валидным пользователем", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
        });
        Allure.step("Проверить переход на страницу Products", () ->
            assertEquals(productsPage.getTitle(), "Products")
        );
    }

    @Epic("UI Automation")
    @Feature("Authentication")
    @Story("Обработка невалидных учетных данных")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test(dataProvider = "incorrectData", priority = 3)
    public void checkLockedOutLogin(User user, String errorMsg) {
        Allure.step("Открыть страницу и выполнить вход с невалидными данными", () -> {
            loginPage.open();
            loginPage.login(user);
        });
        Allure.step("Проверить отображение корректного текста ошибки", () -> {
            assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не появилось");
            assertEquals(loginPage.getErrorMsgText(), errorMsg);
        });
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginIncorrectData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyLogin(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"},
                {withIncorrectPermission(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Epic("UI Automation")
    @Feature("Authentication")
    @Story("Выход из системы")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kotikova Ann")
    @Test(priority = 2)
    public void checkLogOut() {
        Allure.step("Авторизоваться и открыть меню", () -> {
            loginPage.open();
            loginPage.login(withAdminPermission());
            productsPage.navigationPanel.openMenu();
        });
        Allure.step("Выйти из системы и проверить форму логина", () -> {
            productsPage.navigationPanel.logOut();
            assertTrue(loginPage.isLoginFormVisible(), "После выхода не отображается форма входа на сайт");
        });
    }
}
