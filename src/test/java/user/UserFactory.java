package user;

import utils.PropertyReader;

public class UserFactory {
    public static LoginUser withAdminPermission() {
        return new LoginUser(
                PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static LoginUser withLockedPermission() {
        return new LoginUser(
                PropertyReader.getProperty("saucedemo.locked_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static LoginUser withIncorrectPermission() {
        return new LoginUser(
                PropertyReader.getProperty("saucedemo.incorrect_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static LoginUser withEmptyLogin() {
        return new LoginUser(
                "",
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static LoginUser withEmptyPassword() {
        return new LoginUser(
                PropertyReader.getProperty("saucedemo.user"),
                "");
    }

    public static CheckoutUser withCheckoutData() {
        return new CheckoutUser(
                PropertyReader.getProperty("saucedemo.firstname"),
                PropertyReader.getProperty("saucedemo.lastname"),
                PropertyReader.getProperty("saucedemo.zipcode"));
    }
}
