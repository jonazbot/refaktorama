package no.kantega;

import no.kantega.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

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
        float actualPrice = pub.computeCost(Map.of(Menu.HANSA, 1), false);
        assertEquals(74, actualPrice);
    }

    @Test
    @DisplayName("When we order one cider, then the price is 103 kr.")
    public void testCidersAreCostly() {
        float actualPrice = pub.computeCost(Map.of(Menu.GRANS, 1), false);
        assertEquals(103, actualPrice);
    }

    @Test
    @DisplayName("When we order a proper cider, then the price is 110 kr.")
    public void testProperCidersAreEvenMoreExpensive() {
        float actualPrice = pub.computeCost(Map.of(Menu.STRONGBROW, 1), false);
        assertEquals(110, actualPrice);
    }

    @Test
    @DisplayName("When we order a gin and tonic, then the price is 115 kr.")
    public void testACocktail() {
        float actualPrice = pub.computeCost(Map.of(Menu.GT, 1), false);
        assertEquals(115, actualPrice);
    }

    @Test
    @DisplayName("When we order a bacardi special, then the price is 127 kr.")
    public void testBacardiSpecial() {
        float actualPrice = pub.computeCost(Map.of(Menu.BACARDI_SPECIAL, 1), false);
        assertEquals(127, actualPrice);
    }

    @Nested
    @DisplayName("Given a customer who is a student")
    class Students {
        @Test
        @DisplayName("When they order a beer, then they get a discount.")
        public void testStudentsGetADiscountForBeer() {
            float actualPrice = pub.computeCost(Map.of(Menu.HANSA, 1), true);
            assertEquals(67, actualPrice);
        }

        @Test
        @DisplayName("When they order multiple beers, they also get a discount.")
        public void testStudentsGetDiscountsWhenOrderingMoreThanOneBeer() {
            float actualPrice = pub.computeCost(Map.of(Menu.HANSA, 2), true);
            assertEquals(67 * 2, actualPrice);
        }

        @Test
        @DisplayName("When they order a cocktail, they do not get a discount.")
        public void testStudentsDoNotGetDiscountsForCocktails() {
            float actualPrice = pub.computeCost(Map.of(Menu.GT, 1), true);
            assertEquals(115, actualPrice);
        }
    }

    @Nested
    @DisplayName("When they order more than two drinks")
    class MultipleDrinks {
        @Test
        @DisplayName("and the order is for cocktails, then they are refused.")
        public void testCanBuyAtMostTwoDrinksInOneGo() {
            assertThrows(RuntimeException.class, () -> pub.computeCost(Map.of(Menu.BACARDI_SPECIAL, 3), false));
        }

        @Test
        @DisplayName("and the order is for beers, then they are served.")
        public void testCanOrderMoreThanTwoBeers() {
            pub.computeCost(Map.of(Menu.HANSA, 5), false);
        }
    }
}
