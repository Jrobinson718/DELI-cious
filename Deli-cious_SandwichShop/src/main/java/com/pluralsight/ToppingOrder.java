package com.pluralsight;

/**
 * Represents an order for a specific topping, including whether an extra portion is desired.
 * This class encapsulates a {@link Topping} object and a flag for "extra".
 * It also provides a method to calculate the price of this topping order based on
 * the sandwich size and whether an extra portion of a premium topping is requested.
 */
public class ToppingOrder {
    //          === Instance Variables ===
    private Topping topping;
    private boolean isExtra;

    //          === Constructor ===
    public ToppingOrder(Topping topping, boolean isExtra) {
        this.topping = topping;
        this.isExtra = isExtra;
    }

    //          === Getters ===
    public Topping getTopping() {
        return topping;
    }

    public boolean isExtra() {
        return isExtra;
    }

    //          === Methods ===

    /**
     * Calculates the price of this topping order based on the sandwich size.
     * The price includes the base price of the topping for the given size.
     * If {@code isExtra} is true, the topping supports extra portions, and the topping
     * is an instance of {@link PremiumTopping}, then the extra price for the
     * premium topping is added.
     *
     * @param sandwichSize The size of the sandwich for which to calculate the price.
     * @return The total price for this topping order considering its type and whether it's extra.
     */
    public double getPrice(String sandwichSize) {
        double price = topping.getPriceForSize(sandwichSize);

        if (isExtra && topping.supportsExtra() && topping instanceof PremiumTopping premiumTopping){
                price += premiumTopping.getExtraPrice(sandwichSize);
        }

        return price;
    }
}
