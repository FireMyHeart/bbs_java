package user;

public class CheckoutUser {
    private final String firstname;
    private final String lastname;
    private final String zipcode;

    public CheckoutUser(String firstname, String lastname, String zipcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.zipcode = zipcode;
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
        return "CheckoutUser{firstname='" + firstname + "', lastname='" + lastname + "', zipcode='" + zipcode + "'}";
    }
}
