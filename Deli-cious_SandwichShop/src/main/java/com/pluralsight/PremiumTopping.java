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

    /**
     * Calculates the price for an extra portion of this premium topping based on the sandwich size.
     * Note: This method currently returns fixed values based on size and does not use the {@code extraPrice} instance variable.
     *
     * @param size The size of the sandwich.
     * @return The price for an extra portion of the topping. Defaults to 1.00 for unknown sizes.
     */
    public double getExtraPrice(String size){
        if (category.equals(MEAT)){
            switch (size) {
                case "4" -> {return 0.50;}
                case "8" -> {return 1.00;}
                case "12" -> {return 1.50;}
                default -> {return 1.00;}
            }
        } else if (category.equals(CHEESE)) {
            switch (size){
                case "4" -> {return 0.30;}
                case "8" -> {return 0.60;}
                case "12" -> {return 0.90;}
                default -> {return 0.60;}
            }
        }
        return 0;
    }

    /**
     * Gets the base price of this premium topping for a given sandwich size.
     *
     * @param size The size of the sandwich ).
     * @return The price of the topping for the specified size. Defaults to 2.00 for unknown sizes.
     */
    @Override
    public double getPriceForSize(String size){
        if (category.equals(MEAT)){
            switch (size){
                case "4" -> {return 1.00;}
                case "8" -> {return 2.00;}
                case "12" -> {return 3.00;}
                default -> {return 2.00;}
            }
        }else if (category.equals(CHEESE)){
            switch (size){
                case "4" -> {return 0.75;}
                case "8" -> {return 1.50;}
                case "12" -> {return 2.25;}
                default -> {return 1.50;}
            }
        }
        return 0;
    }

    @Override
    public boolean supportsExtra(){
        return true;
    }
}
