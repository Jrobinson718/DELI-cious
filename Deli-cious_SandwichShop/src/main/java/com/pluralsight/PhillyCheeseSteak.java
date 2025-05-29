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
        Topping steak = Topping.getByNumber(1);
        Topping americanCheese = Topping.getByNumber(7);
        Topping peppers = Topping.getByNumber(12);
        Topping mayo = Topping.getByNumber(20);

        this.addTopping(steak, false);
        this.addTopping(americanCheese, false);
        this.addTopping(peppers, false);
        this.addTopping(mayo, false);
        this.setToasted(true);
    }
}
