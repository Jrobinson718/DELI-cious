package com.pluralsight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

    private Sandwich sandwich;
    private PremiumTopping ham;
    private PremiumTopping cheese;
    private RegularTopping lettuce;
    private RegularTopping mayo;

    @BeforeEach
    void setUp() {
        sandwich = new Sandwich("8", "white");

        ham = new PremiumTopping("Ham", PremiumTopping.MEAT, 1.00, 0.50);
        cheese = new PremiumTopping("American", PremiumTopping.CHEESE, 0.75, 0.30);
        lettuce = new RegularTopping("Lettuce", RegularTopping.REGULAR);

//        cheese = new PremiumTopping("American", PremiumTopping.CHEESE, 0.75, 0.30);
//        lettuce = new RegularTopping("Lettuce", RegularTopping.REGULAR);
      //  Topping t = new Topping("American", ToppingType.CHEESE, isExtra, sizeOfSandwitch);

        mayo = new RegularTopping("Mayo", RegularTopping.SAUCE);
    }

    @Test
    void getSize() {
        assertEquals("8", sandwich.getSize());

        sandwich.setSize("12");
        assertEquals("12", sandwich.getSize());
    }

    @Test
    void isToasted() {
        assertFalse(sandwich.isToasted());

        sandwich.setToasted(true);
        assertTrue(sandwich.isToasted());
    }

    @Test
    void setToasted() {
        sandwich.setToasted(true);
        assertTrue(sandwich.isToasted());

        sandwich.setToasted(false);
        assertFalse(sandwich.isToasted());
    }

    @Test
    void setSize() {
        sandwich.setSize("4");
        assertEquals("4", sandwich.getSize());

        sandwich.setSize("small");
        assertEquals("4", sandwich.getSize());

        sandwich.setSize("LARGE");
        assertEquals("12", sandwich.getSize());

        assertThrows(IllegalArgumentException.class, () -> {
            sandwich.setSize("20");
        });
    }

    @Test
    void getBreadType() {
        assertEquals("White", sandwich.getBreadType());

        sandwich.setBreadType("wheat");
        assertEquals("Wheat", sandwich.getBreadType());
    }

    @Test
    void setBreadType() {
        sandwich.setBreadType("wheat");
        assertEquals("Wheat", sandwich.getBreadType());

        sandwich.setBreadType("w");
        assertEquals("White", sandwich.getBreadType());

        sandwich.setBreadType("tortilla");
        assertEquals("Wrap", sandwich.getBreadType());

        assertThrows(IllegalArgumentException.class, () -> {
            sandwich.setBreadType("sourdough");
        });
    }

    @Test
    void getToppings() {
        List<ToppingOrder> toppings = sandwich.getToppings();
        assertTrue(toppings.isEmpty());

        sandwich.addTopping(ham, false);
        sandwich.addTopping(cheese, true);

        toppings = sandwich.getToppings();
        assertEquals(2, toppings.size());

        List<ToppingOrder> finalToppings = toppings;
        assertThrows(UnsupportedOperationException.class, () -> {
            finalToppings.add(new ToppingOrder(lettuce, false));
        });
    }

    @Test
    void addTopping() {
        sandwich.addTopping(ham, false);
        assertEquals(1, sandwich.getToppings().size());

        sandwich.addTopping(cheese, true);
        assertEquals(2, sandwich.getToppings().size());

        sandwich.addTopping(lettuce, true);
        assertEquals(3, sandwich.getToppings().size());
        assertFalse(sandwich.getToppings().get(2).isExtra());

        sandwich.addTopping(null, true);
        assertEquals(3, sandwich.getToppings().size());
    }

    @Test
    void removeTopping() {
        sandwich.addTopping(ham, false);
        sandwich.addTopping(ham, true);
        sandwich.addTopping(cheese, false);

        sandwich.removeTopping(ham);

        assertEquals(1, sandwich.getToppings().size());
        assertEquals(cheese, sandwich.getToppings().get(0).getTopping());

        sandwich.removeTopping(lettuce);
        assertEquals(1, sandwich.getToppings().size());

        sandwich.removeTopping(null);
        assertEquals(1, sandwich.getToppings().size());
    }

    @Test
    void testRemoveTopping() {
        sandwich.addTopping(ham, false);
        sandwich.addTopping(ham, true);
        sandwich.addTopping(cheese, false);

        sandwich.removeTopping(ham, true);

        assertEquals(2, sandwich.getToppings().size());

        ToppingOrder remainingHam = sandwich.getToppings().stream()
                .filter(order -> order.getTopping().equals(ham))
                .findFirst()
                .orElse(null);

        assertNotNull(remainingHam);
        assertFalse(remainingHam.isExtra());

        sandwich.removeTopping(ham, true);
        assertEquals(2, sandwich.getToppings().size());
    }

    @Test
    void getPrice() {
        assertEquals(7.00, sandwich.getPrice(), 0.01);

        sandwich.addTopping(ham, false);
        sandwich.addTopping(cheese, true);
        sandwich.addTopping(lettuce, false);

        assertEquals(11.10, sandwich.getPrice(), 0.01);

        Sandwich smallSandwich = new Sandwich("4", "wheat");
        smallSandwich.addTopping(ham, true);

        assertEquals(7.00, smallSandwich.getPrice(), 0.01);
    }

    @Test
    void testNameGeneration() {
        assertEquals("8\" White sandwich", sandwich.getName());

        sandwich.setSize("12");
        assertEquals("12\" White sandwich", sandwich.getName());

        sandwich.setBreadType("rye");
        assertEquals("12\" Rye sandwich", sandwich.getName());
    }

    @Test
    void testSizeVariations() {
        String[] fourInchVariations = {"4", "4\"", "small", "s", "SMALL"};
        for (String size : fourInchVariations) {
            Sandwich testSandwich = new Sandwich(size, "white");
            assertEquals("4", testSandwich.getSize(),
                    "Failed for input: " + size);
        }
    }
}