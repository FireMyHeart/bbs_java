package tests;

import org.openqa.selenium.By;
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

    @Test
    public void checkLockedOutLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не появилось");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("test_user", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "Сообщение об ошибке не появилось");
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username and password do not match any user in this service");
    }
}