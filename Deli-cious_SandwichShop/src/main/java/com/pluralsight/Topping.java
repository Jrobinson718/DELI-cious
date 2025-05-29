package com.pluralsight;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Topping {

    //          === Constant Variables ===
    public static final String MEAT = "Meat";
    public static final String CHEESE = "Cheese";
    public static final String REGULAR = "Regular";
    public static final String SAUCE = "Sauce";
    public static final String SIDE = "Side";

    //          === Instance Variables ===
    private String name;
    private final String category;
    private boolean isPremium;

    private static final Map<Integer,Topping> AVAILABLE_TOPPINGS = new HashMap<>();
    static {
        AVAILABLE_TOPPINGS.put(1, new Topping("Steak", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(2, new Topping("Ham", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(3, new Topping("Salami", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(4, new Topping("Pastrami", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(5, new Topping("Chicken", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(6, new Topping("Bacon", Topping.MEAT, true));
        AVAILABLE_TOPPINGS.put(7, new Topping("American", Topping.CHEESE, true));
        AVAILABLE_TOPPINGS.put(8, new Topping("Provolone", Topping.CHEESE, true));
        AVAILABLE_TOPPINGS.put(9, new Topping("Cheddar", Topping.CHEESE, true));
        AVAILABLE_TOPPINGS.put(10, new Topping("Swiss", Topping.CHEESE, true));
        AVAILABLE_TOPPINGS.put(11, new Topping("Lettuce", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(12, new Topping("Peppers", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(13, new Topping("Onions", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(14, new Topping("Tomatoes", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(15, new Topping("Jalapeños", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(16, new Topping("Cucumbers", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(17, new Topping("Pickles", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(18, new Topping("Guacamole", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(19, new Topping("Mushrooms", Topping.REGULAR, false));
        AVAILABLE_TOPPINGS.put(20, new Topping("Mayo", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(21, new Topping("Mustard", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(22, new Topping("Ketchup", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(23, new Topping("Ranch", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(24, new Topping("Thousand Islands", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(25, new Topping("Vinaigrette", Topping.SAUCE, false));
        AVAILABLE_TOPPINGS.put(26, new Topping("Au Jus", Topping.SIDE, false));
        AVAILABLE_TOPPINGS.put(27, new Topping("French Fries", Topping.SIDE, false));
        AVAILABLE_TOPPINGS.put(28, new Topping("Coleslaw", Topping.SIDE, false));
        AVAILABLE_TOPPINGS.put(29, new Topping("Pickles", Topping.SIDE, false));
        AVAILABLE_TOPPINGS.put(30, new Topping("DELI-cious Dip", Topping.SIDE, false));
    }

    //          === Constructor ===
    public Topping(String name, String category, boolean isPremium) {
        this.name = name;
        this.category = category;
        this.isPremium = isPremium;
    }

    //          === Methods to access toppings ===

    public static Topping getByNumber(int number) {
        return AVAILABLE_TOPPINGS.get(number);
    }

    public static Map<Integer, Topping> getToppingByCategory(String category) {
        return AVAILABLE_TOPPINGS.entrySet().stream()
                .filter(entry -> entry.getValue().getCategory().equals(category))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, Topping> getAvailableToppings() {
        return new HashMap<>(AVAILABLE_TOPPINGS);
    }

    //          === Instance methods ===

    /**
     * Gets the base price of this premium topping for a given sandwich size.
     *
     * @param size The size of the sandwich ).
     * @return The price of the topping for the specified size. Defaults to 2.00 for unknown sizes.
     */
    private double getBasePrice(String size) {
        if (!isPremium) return 0.0;

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

    /**
     * Calculates the price for an extra portion of this premium topping based on the sandwich size.
     * Note: This method currently returns fixed values based on size and does not use the {@code extraPrice} instance variable.
     *
     * @param size The size of the sandwich.
     * @return The price for an extra portion of the topping. Defaults to 1.00 for unknown sizes.
     */
    private double getExtraPrice(String size) {
        if (!isPremium) return 0.0;

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

    public boolean supportsExtra() {
        return isPremium;
    }

    //          === Getters ===
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public boolean isPremium() {
        return isPremium;
    }

    public double getPrice(String size, boolean isExtra) {
        double price = getBasePrice(size);

        if (isExtra && supportsExtra()) {
            price += getExtraPrice(size);
        }
        return price;
    }
}