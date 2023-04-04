package no.kantega;

import java.util.Set;

enum Discount {
    NO_DISCOUNT("", 1, Set.of()),
    STUDENT_DISCOUNT("Student discount", 0.9F, Set.of(Menu.Type.BEER_OR_CIDER));

    private final String name;
    private final float amount;
    private final Set<Menu.Type> validDiscountItems;

    Discount(String discountName, float discount, Set<Menu.Type> validDiscountItems) {
        this.name = discountName;
        this.amount = discount;
        this.validDiscountItems = validDiscountItems;
    }

    String getName() {
        return this.name;
    }

    float getAmount() {
        return this.amount;
    }

    boolean isValidForItem(Menu item) {
        if (this.equals(NO_DISCOUNT)) return true;
        return this.validDiscountItems.contains(item.getType());
    }
}
