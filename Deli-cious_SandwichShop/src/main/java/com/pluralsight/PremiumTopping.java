package com.pluralsight;

public class PremiumTopping extends Topping{
    //          === Constant Variables ===
    public static final String MEAT = "Meat";
    public static final String CHEESE = "Cheese";

    //          === Instance Variables ===
    private double basePrice;
    private double extraPrice;

    //          === Constructor ===

    public PremiumTopping(String name, String category, double basePrice, double extraPrice) {
        super(name, category);
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
    }

    //          === Methods ===

    public double getExtraPrice(String size){
        switch (size) {
            case "4" -> {return 0.50;}
            case "8" -> {return 1.00;}
            case "12" -> {return 1.50;}
            default -> {return 1.00;}
        }
    }

    @Override
    public double getPriceForSize(String size){
        switch (size){
            case "4" -> {return 1.00;}
            case "8" -> {return 2.00;}
            case "12" -> {return 3.00;}
            default -> {return 2.00;}
        }
    }

    @Override
    public boolean supportsExtra(){
        return true;
    }
}
