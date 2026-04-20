package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(dataProvider = "incorrectData")
    public void checkLockedOutLogin(String login, String password, String ErrorMsg) {
        loginPage.open();
        loginPage.login(login, password);
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не появилось");
        assertEquals(loginPage.getErrorMsgText(), ErrorMsg);
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginIncorrectData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test
    public void checkLogOut() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.navigationPanel.openMenu();
        productsPage.navigationPanel.logOut();
        assertTrue(loginPage.isLoginFormVisible(), "После выхода не отображается форма входа на сайт");
    }
}
