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
    public abstract double getPriceForSize(String size);
    public abstract boolean supportsExtra();

    //          === Getters ===
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
