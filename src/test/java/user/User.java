package user;

public class User {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String zipcode;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String firstname, String lastname, String zipcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public String toString() {
        if (login != null) {
            return "User{login='" + login + "', password='" + password + "'}";
        }
        return "User{firstname='" + firstname + "', lastname='" + lastname + "', zipcode='" + zipcode + "'}";
    }
}
