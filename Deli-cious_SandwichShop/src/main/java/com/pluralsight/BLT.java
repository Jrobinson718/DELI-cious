package com.pluralsight;

public class BLT extends SignatureSandwich{

    //          === Constructor ===
    public BLT() {
        super("BLT", "8", "white");
    }

    //          === Abstract Method ===
    /**
     * Applies the predefined signature toppings and settings for a BLT sandwich.
     * This method is called by the {@link SignatureSandwich} constructor to populate
     * the sandwich with its specific ingredients.
     * <p>
     * For a BLT, this includes:
     * <ul>
     * <li>Bacon (Premium Meat)</li>
     * <li>Cheddar (Premium Cheese)</li>
     * <li>Lettuce (Regular Topping)</li>
     * <li>Tomato (Regular Topping)</li>
     * <li>Ranch (Sauce)</li>
     * </ul>
     * The sandwich is also set to be toasted by default.
     * </p>
     */
    @Override
    protected void applySignatureToppings() {
        PremiumTopping bacon = new PremiumTopping("Bacon", PremiumTopping.MEAT, 1.00, 0.50);
        PremiumTopping cheddar = new PremiumTopping("Cheddar", PremiumTopping.CHEESE, .75, .30);
        RegularTopping lettuce = new RegularTopping("Lettuce", RegularTopping.REGULAR);
        RegularTopping tomato = new RegularTopping("Tomato", RegularTopping.REGULAR);
        RegularTopping ranch = new RegularTopping("Ranch", RegularTopping.SAUCE);

        this.addTopping(bacon, false);
        this.addTopping(cheddar, false);
        this.addTopping(lettuce, false);
        this.addTopping(tomato, false);
        this.addTopping(ranch, false);

        this.setToasted(true);
    }
}

