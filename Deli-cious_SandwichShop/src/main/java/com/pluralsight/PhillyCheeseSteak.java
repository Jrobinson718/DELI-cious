package com.pluralsight;

public class PhillyCheeseSteak extends SignatureSandwich{
    //          === Constructor ===

    public PhillyCheeseSteak() {
        super("Philly Cheese Steak", "8", "White");
    }

    //          === Abstract Method ===
    /**
     * Applies the predefined signature toppings and settings for a Philly Cheese Steak sandwich.
     * This method is called by the {@link SignatureSandwich} constructor to populate
     * the sandwich with its specific ingredients.
     * <p>
     * For a Philly Cheese Steak, this includes:
     * <ul>
     * <li>Steak (Premium Meat)</li>
     * <li>American Cheese (Premium Cheese)</li>
     * <li>Peppers (Regular Topping)</li>
     * <li>Mayo (Sauce)</li>
     * </ul>
     * The sandwich is also set to be toasted by default.
     * </p>
     */
    @Override
    protected void applySignatureToppings(){
        PremiumTopping steak = new PremiumTopping("Steak", PremiumTopping.MEAT, 1.00, .50);
        PremiumTopping americanCheese = new PremiumTopping("American Cheese", PremiumTopping.CHEESE, .75, .30);
        RegularTopping peppers = new RegularTopping("Peppers", RegularTopping.REGULAR);
        RegularTopping mayo = new RegularTopping("Mayo", RegularTopping.SAUCE);

        this.addTopping(steak, false);
        this.addTopping(americanCheese, false);
        this.addTopping(peppers, false);
        this.addTopping(mayo, false);
        this.setToasted(true);
    }
}
