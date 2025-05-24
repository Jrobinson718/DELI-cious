package com.pluralsight;

public abstract class Topping {
    //          === Instance Variables ===
    protected String name;
    protected String category;

    protected Topping(String name, String category) {
        this.name = name;
        this.category = category;
    }

    //          === Abstract Methods ===
    /**
     * Calculates the price of the topping based on a given size.
     * This method must be implemented by concrete subclasses.
     *
     * @param size The size for which to determine the topping's price (e.g., "Small", "8\"", "Large").
     * @return The price of the topping for the specified size.
     */
    public abstract double getPriceForSize(String size);
    /**
     * Determines if this topping supports an "extra" portion option.
     * This method must be implemented by concrete subclasses.
     *
     * @return {@code true} if an extra portion of this topping is supported, {@code false} otherwise.
     */
    public abstract boolean supportsExtra();

    //          === Getters ===
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
