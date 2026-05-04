package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckoutUser {
    private final String firstname;
    private final String lastname;
    private final String zipcode;

    @Override
    public String toString() {
        return "CheckoutUser{firstname='" + firstname + "', lastname='" + lastname + "', zipcode='" + zipcode + "'}";
    }
}
