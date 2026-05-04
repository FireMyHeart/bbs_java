package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUser {
    private final String login;
    private final String password;

    @Override
    public String toString() {
        return "LoginUser{login='" + login + "', password='" + password + "'}";
    }
}
