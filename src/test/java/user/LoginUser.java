package user;

public class LoginUser {
    private final String login;
    private final String password;

    public LoginUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "LoginUser{login='" + login + "', password='" + password + "'}";
    }
}
