package no.kantega;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Pub spec")
public class PubPricesTest {

    private Pub pub;

    @BeforeEach
    public void setUp() {
        pub = new Pub();
    }

    @Test
    @DisplayName("When we order one beer, then the price is 74 kr.")
    public void oneBeerTest() {
        pub.order(Menu.HANSA, 1, Discount.NO_DISCOUNT);
        assertEquals(74, pub.computeBill());
    }

    @Test
    @DisplayName("When we order one cider, then the price is 103 kr.")
    public void testCidersAreCostly() {
        pub.order(Menu.GRANS, 1, Discount.NO_DISCOUNT);
        assertEquals(103, pub.computeBill());
    }

    @Test
    @DisplayName("When we order a proper cider, then the price is 110 kr.")
    public void testProperCidersAreEvenMoreExpensive() {
        pub.order(Menu.STRONGBROW, 1, Discount.NO_DISCOUNT);
        assertEquals(110, pub.computeBill());
    }

    @Test
    @DisplayName("When we order a gin and tonic, then the price is 115 kr.")
    public void testACocktail() {
        pub.order(Menu.GT, 1, Discount.NO_DISCOUNT);
        assertEquals(115, pub.computeBill());
    }

    @Test
    @DisplayName("When we order a bacardi special, then the price is 127 kr.")
    public void testBacardiSpecial() {
        pub.order(Menu.BACARDI_SPECIAL, 1, Discount.NO_DISCOUNT);
        assertEquals(127, pub.computeBill());
    }

    @Nested
    @DisplayName("Given a customer who is a student")
    class Students {
        @Test
        @DisplayName("When they order a beer, then they get a discount.")
        public void testStudentsGetADiscountForBeer() {
            pub.order(Menu.HANSA, 1, Discount.STUDENT_DISCOUNT);
            assertEquals(67, pub.computeBill());
        }

        @Test
        @DisplayName("When they order multiple beers, they also get a discount.")
        public void testStudentsGetDiscountsWhenOrderingMoreThanOneBeer() {
            pub.order(Menu.HANSA, 2, Discount.STUDENT_DISCOUNT);
            assertEquals(67 * 2, pub.computeBill());
        }

        @Test
        @DisplayName("When they order a cocktail, they do not get a discount.")
        public void testStudentsDoNotGetDiscountsForCocktails() {
            assertThrows(RuntimeException.class, () -> pub.order(Menu.GT, 1, Discount.STUDENT_DISCOUNT));
        }
    }

    @Nested
    @DisplayName("When they order more than two drinks")
    class MultipleDrinks {
        @Test
        @DisplayName("and the order is for cocktails, then they are refused.")
        public void testCanBuyAtMostTwoDrinksInOneGo() {
            assertThrows(RuntimeException.class, () -> pub.order(Menu.BACARDI_SPECIAL, 3, Discount.NO_DISCOUNT));
        }

        @Test
        @DisplayName("and the order is for beers, then they are served.")
        public void testCanOrderMoreThanTwoBeers() {
            pub.order(Menu.HANSA, 5, Discount.NO_DISCOUNT);
            assertEquals(Menu.HANSA.getPrice() * 5, pub.computeBill());
        }
    }

    @Nested
    @DisplayName("Add tips")
    class Tips {
        @Test
        @DisplayName("Add tips to cost of one beer")
        public void testCanAddTips() {
            int tips = 20;
            pub.order(Menu.HANSA, 1, Discount.NO_DISCOUNT);
            assertEquals(Menu.HANSA.getPrice() + tips, pub.addTips(pub.computeBill(), tips));
        }
    }
}
