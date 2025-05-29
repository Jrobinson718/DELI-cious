package com.pluralsight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

@DisplayName("Sandwich Class Tests")
public class SandwichTest {

    private Sandwich sandwich;
    private Topping lettuce;
    private Topping steak;
    private Topping american;
    private Topping mayo;

    @BeforeEach
    void setUp() {
        sandwich = new Sandwich("8", "White");

        lettuce = new Topping("Lettuce", Topping.REGULAR, false);
        steak = new Topping("Steak", Topping.MEAT, true);
        american = new Topping("American", Topping.CHEESE, true);
        mayo = new Topping("Mayo", Topping.SAUCE, false);
    }

    @Test
    void constructor_ShouldInitializeCorrectly() {
        org.junit.jupiter.api.Assertions.assertEquals("8\" White sandwich", sandwich.getName());
        org.junit.jupiter.api.Assertions.assertEquals("8", sandwich.getSize());
        org.junit.jupiter.api.Assertions.assertEquals("White", sandwich.getBreadType());
        org.junit.jupiter.api.Assertions.assertFalse(sandwich.isToasted());
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppings().isEmpty());
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppingExtraStatuses().isEmpty());
    }

    @Test
    void addTopping_ShouldAddRegularTopping() {
        sandwich.addTopping(lettuce, false);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(lettuce, sandwich.getToppings().get(0));
        org.junit.jupiter.api.Assertions.assertFalse(sandwich.getToppingExtraStatuses().get(0));
    }

    @Test
    void addTopping_ShouldAddPremiumRegularTopping() {
        sandwich.addTopping(steak, false);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(steak, sandwich.getToppings().get(0));
        org.junit.jupiter.api.Assertions.assertFalse(sandwich.getToppingExtraStatuses().get(0));
    }

    @Test
    void addTopping_ShouldAddPremiumExtraTopping() {
        sandwich.addTopping(steak, true);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(steak, sandwich.getToppings().get(0));
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppingExtraStatuses().get(0));
    }

    @Test
    void addTopping_ShouldHandleRegularToppingAsExtra() {
        sandwich.addTopping(lettuce, true);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(lettuce, sandwich.getToppings().get(0));
        org.junit.jupiter.api.Assertions.assertFalse(sandwich.getToppingExtraStatuses().get(0));
    }

    @Test
    void addTopping_ShouldHandleNullTopping() {
        sandwich.addTopping(null, false);
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppings().isEmpty());
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppingExtraStatuses().isEmpty());
    }

    @Test
    void addTopping_ShouldAddMultipleToppings() {
        sandwich.addTopping(steak, true);
        sandwich.addTopping(lettuce, false);
        sandwich.addTopping(american, false);
        org.junit.jupiter.api.Assertions.assertEquals(3, sandwich.getToppings().size());
        List<String> toppingNames = sandwich.getToppings().stream().map(Topping::getName).collect(Collectors.toList());
        org.junit.jupiter.api.Assertions.assertTrue(toppingNames.contains("Steak"));
        org.junit.jupiter.api.Assertions.assertTrue(toppingNames.contains("Lettuce"));
        org.junit.jupiter.api.Assertions.assertTrue(toppingNames.contains("American"));
    }

    @Test
    void removeTopping_Topping_ShouldRemoveAllOccurrences() {
        sandwich.addTopping(lettuce, false);
        sandwich.addTopping(steak, false);
        sandwich.addTopping(lettuce, true);
        org.junit.jupiter.api.Assertions.assertEquals(3, sandwich.getToppings().size());

        sandwich.removeTopping(lettuce);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(steak, sandwich.getToppings().get(0));
    }

    @Test
    void removeTopping_Topping_NotFound() {
        sandwich.addTopping(steak, false);
        sandwich.removeTopping(lettuce);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
    }

    @Test
    void removeTopping_Topping_Null() {
        sandwich.addTopping(steak, false);
        sandwich.removeTopping(null);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
    }

    @Test
    void removeTopping_ToppingExtra_ShouldRemoveSpecific() {
        sandwich.addTopping(steak, false);
        sandwich.addTopping(steak, true);
        sandwich.addTopping(lettuce, false);

        org.junit.jupiter.api.Assertions.assertEquals(3, sandwich.getToppings().size());

        sandwich.removeTopping(steak, false);
        org.junit.jupiter.api.Assertions.assertEquals(2, sandwich.getToppings().size());
        org.junit.jupiter.api.Assertions.assertEquals(steak, sandwich.getToppings().get(0));
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.getToppingExtraStatuses().get(0));
        org.junit.jupiter.api.Assertions.assertEquals(lettuce, sandwich.getToppings().get(1));
    }

    @Test
    void removeTopping_ToppingExtra_NotFound() {
        sandwich.addTopping(steak, true);
        sandwich.removeTopping(steak, false);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
    }

    @Test
    void removeTopping_ToppingExtra_Null() {
        sandwich.addTopping(steak, false);
        sandwich.removeTopping(null, true);
        org.junit.jupiter.api.Assertions.assertEquals(1, sandwich.getToppings().size());
    }

    @Test
    void setToasted_ShouldSetTrue() {
        sandwich.setToasted(true);
        org.junit.jupiter.api.Assertions.assertTrue(sandwich.isToasted());
    }

    @Test
    void setToasted_ShouldSetFalse() {
        sandwich.setToasted(true);
        sandwich.setToasted(false);
        org.junit.jupiter.api.Assertions.assertFalse(sandwich.isToasted());
    }

    @Test
    void getPrice_EmptySandwich() {
        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        org.junit.jupiter.api.Assertions.assertEquals(expectedBreadPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithRegularToppings() {
        sandwich.addTopping(lettuce, false);
        sandwich.addTopping(mayo, false);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingPrice = 0.0 + 0.0;
        double expectedTotalPrice = expectedBreadPrice + expectedToppingPrice;
        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithPremiumMeatRegular() {
        sandwich.addTopping(steak, false);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingPrice = steak.getPrice("8", false);
        double expectedTotalPrice = expectedBreadPrice + expectedToppingPrice;
        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithPremiumMeatExtra() {
        sandwich.addTopping(steak, true);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingPrice = steak.getPrice("8", true);
        double expectedTotalPrice = expectedBreadPrice + expectedToppingPrice;
        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithPremiumCheeseRegular() {
        sandwich.addTopping(american, false);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingPrice = american.getPrice("8", false);
        double expectedTotalPrice = expectedBreadPrice + expectedToppingPrice;
        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithPremiumCheeseExtra() {
        sandwich.addTopping(american, true);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingPrice = american.getPrice("8", true);
        double expectedTotalPrice = expectedBreadPrice + expectedToppingPrice;
        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getPrice_WithMixedToppingsAndExtras() {
        sandwich.addTopping(lettuce, false);
        sandwich.addTopping(mayo, false);
        sandwich.addTopping(steak, false);
        sandwich.addTopping(american, true);
        sandwich.addTopping(steak, true);

        double expectedBreadPrice = new Bread("8", "White").getBasePrice();
        double expectedToppingsTotal = lettuce.getPrice("8", false) +
                mayo.getPrice("8", false) +
                steak.getPrice("8", false) +
                american.getPrice("8", true) +
                steak.getPrice("8", true);
        double expectedTotalPrice = expectedBreadPrice + expectedToppingsTotal;

        org.junit.jupiter.api.Assertions.assertEquals(expectedTotalPrice, sandwich.getPrice(), 0.001);
    }

    @Test
    void getToppings_ShouldReturnUnmodifiableList() {
        sandwich.addTopping(lettuce, false);
        List<Topping> toppings = sandwich.getToppings();
        org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> toppings.add(mayo));
    }

    @Test
    void getToppingExtraStatuses_ShouldReturnUnmodifiableList() {
        sandwich.addTopping(steak, true);
        List<Boolean> extraStatuses = sandwich.getToppingExtraStatuses();
        org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> extraStatuses.add(false));
    }
}