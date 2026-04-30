package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.locked_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withIncorrectPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.incorrect_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyLogin() {
        return new User(
                "",
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withEmptyPassword() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                "");
    }

    public static User withCheckoutData() {
        return new User(
                PropertyReader.getProperty("saucedemo.firstname"),
                PropertyReader.getProperty("saucedemo.lastname"),
                PropertyReader.getProperty("saucedemo.zipcode"));
    }
}
