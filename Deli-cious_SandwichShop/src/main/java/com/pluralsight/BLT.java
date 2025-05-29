package com.pluralsight;

public class BLT extends SignatureSandwich{

    //          === Constructor ===
    public BLT() {
        super("BLT", "8", "White");
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
        Topping bacon = Topping.getByNumber(6);
        Topping cheddar = Topping.getByNumber(9);
        Topping lettuce = Topping.getByNumber(11);
        Topping tomatoes = Topping.getByNumber(14);
        Topping mayo = Topping.getByNumber(20);


        this.addTopping(bacon, false);
        this.addTopping(cheddar, false);
        this.addTopping(lettuce, false);
        this.addTopping(tomatoes, false);
        this.addTopping(mayo, false);

        this.setToasted(true);
    }
}

