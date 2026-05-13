package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT("Checkout: Your Information"),
    OVERVIEW("Checkout: Overview"),
    COMPLETE("Checkout: Complete!");

    private final String displayName;
}
