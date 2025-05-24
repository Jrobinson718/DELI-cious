package com.pluralsight;

public class RegularTopping extends Topping{
    //          === Constant Variables ===
    public static final String REGULAR = "Regular";
    public static final String SAUCE = "Sauce";
    public static final String SIDE = "Side";

    //          === Super Class Constructor ===
    public RegularTopping(String name, String category) {
        super(name, category);
    }

    //          === Abstract methods from Topping class ===
    @Override
    public double getPriceForSize(String size){
        return 0;
    }

    @Override
    public boolean supportsExtra(){
        return false;
    }
}
