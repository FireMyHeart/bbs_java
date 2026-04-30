package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test(description = "Авторизация существующего пользователя в системе", priority = 1)
    public void checkLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(dataProvider = "incorrectData", priority = 3)
    public void checkLockedOutLogin(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не появилось");
        assertEquals(loginPage.getErrorMsgText(), errorMsg);
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

    @Test(priority = 2)
    public void checkLogOut() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.logOut();
        assertTrue(loginPage.isLoginFormVisible(), "После выхода не отображается форма входа на сайт");
    }
}
