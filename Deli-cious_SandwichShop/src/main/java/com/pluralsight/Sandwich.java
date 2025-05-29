package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Sandwich extends MenuItem implements Priceable{
    //          === Constant Variables ===
    public static final String SIZE_FOUR = "4";
    public static final String SIZE_EIGHT = "8";
    public static final String SIZE_TWELVE = "12";
    public static final String BREAD_WHITE = "White";
    public static final String BREAD_WHEAT = "Wheat";
    public static final String BREAD_RYE = "Rye";
    public static final String BREAD_WRAP = "Wrap";

    /**
     * A static, unmodifiable map that defines various common string inputs for sandwich sizes
     * and maps them to a standardized size constant (@code SIZE_FOUR}, {@code SIZE_EIGHT}, {@code SIZE_TWELVE}).
     * This map is used by the {@code normalizeSize} method to convert user input
     * (like "4", "small", "s") into a consistent internal representation.
     * The keys are the lowercase string variations, and the values are the standardized size strings.
     */
    private static final Map<String, String> SIZE_VARIATIONS = Map.ofEntries(
            Map.entry("4", SIZE_FOUR),
            Map.entry("4\"", SIZE_FOUR),
            Map.entry("4 inch", SIZE_FOUR),
            Map.entry("four", SIZE_FOUR),
            Map.entry("small", SIZE_FOUR),
            Map.entry("s", SIZE_FOUR),
            Map.entry("sm", SIZE_FOUR),

            Map.entry("8", SIZE_EIGHT),
            Map.entry("8\"", SIZE_EIGHT),
            Map.entry("8 inch", SIZE_EIGHT),
            Map.entry("eight", SIZE_EIGHT),
            Map.entry("medium", SIZE_EIGHT),
            Map.entry("m", SIZE_EIGHT),
            Map.entry("med", SIZE_EIGHT),
            Map.entry("mid", SIZE_EIGHT),

            Map.entry("12", SIZE_TWELVE),
            Map.entry("12\"", SIZE_TWELVE),
            Map.entry("12 inch", SIZE_TWELVE),
            Map.entry("twelve", SIZE_TWELVE),
            Map.entry("large", SIZE_TWELVE),
            Map.entry("l", SIZE_TWELVE),
            Map.entry("lg", SIZE_TWELVE)
    );

    /**
     * A static, unmodifiable map that defines various common string inputs for bread types
     * and maps them to a standardized bread type constant (e.g., {@code BREAD_WHITE}, {@code BREAD_WHEAT}).
     * This map is used by the {@code normalizeBreadType} method to convert user input
     * (like "white", "w", "whole wheat") into a consistent internal representation.
     * The keys are the lowercase string variations, and the values are the standardized bread type strings.
     */
    private static final Map<String, String> BREAD_VARIATIONS = Map.ofEntries(
            Map.entry("white", BREAD_WHITE),
            Map.entry("w", BREAD_WHITE),
            Map.entry("wheat", BREAD_WHEAT),
            Map.entry("wh", BREAD_WHEAT),
            Map.entry("whole wheat", BREAD_WHEAT),
            Map.entry("rye", BREAD_RYE),
            Map.entry("r", BREAD_RYE),
            Map.entry("wrap", BREAD_WRAP),
            Map.entry("tortilla", BREAD_WRAP)
    );

    //          === Instance Variables ===
    private String size;
    private String breadType;
    private List<Topping> toppings;
    private List<Boolean> isExtra;
    private boolean isToasted;

    //          === Constructor ===
    public Sandwich(String size, String breadType) {
        super("Sandwich");
        this.toppings = new ArrayList<>();
        this.isExtra = new ArrayList<>();
        this.isToasted = false;
        setSize(size);
        setBreadType(breadType);
    }

    //          === Methods ===

    /**
     * Normalizes and validates the input string representing a size.
     * It trims whitespace, converts to lowercase, and checks against a predefined map
     * of valid size variations ({@code SIZE_VARIATIONS}).
     *
     * @param input The raw string input for the size.
     * @return The standardized string representation of the size if valid, otherwise {@code null}.
     */
    private String normalizeSize(String input) {
        if (input == null) {
            return null;
        }

        String standardize = input.trim().toLowerCase();

        return SIZE_VARIATIONS.entrySet().stream()
                .filter(entry -> entry.getKey().equals(standardize))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    /**
     * Sets the size of the sandwich.
     * The input size is first normalized and validated. If the size is valid,
     * the internal {@code size} field is updated, and the sandwich name is refreshed.
     * If the size is invalid, an {@link IllegalArgumentException} is thrown.
     *
     * @param size The desired size of the sandwich.
     * @throws IllegalArgumentException if the provided size is not among the valid options.
     */
    public void setSize(String size) {
        String normalizedSize = normalizeSize(size);

        if (normalizedSize != null) {
            this.size = normalizedSize;
            updateName();
        } else {
            throw new IllegalArgumentException(
                    "\nInvalid size: '" + size + "'. Valid sizes include: (4, 8, 12, 'Small', 'Medium', 'Large').\n"
            );
        }
    }

    /**
     * Normalizes and validates the input string representing a bread type.
     * It trims whitespace, converts to lowercase, and checks against a predefined map
     * of valid bread variations ({@code BREAD_VARIATIONS}).
     *
     * @param input The raw string input for the bread type.
     * @return The standardized string representation of the bread type if valid, otherwise {@code null}.
     */
    private String normalizeBreadType (String input) {
        if (input == null) {
            return null;
        }

        String standardize = input.trim().toLowerCase();

        return BREAD_VARIATIONS.entrySet().stream()
                .filter(entry -> entry.getKey().equals(standardize))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public String getBreadType() {
        return breadType;
    }

    /**
     * Sets the bread type of the sandwich.
     * The input bread type is first normalized and validated. If the bread type is valid,
     * the internal {@code breadType} field is updated, and the sandwich name is refreshed.
     * If the bread type is invalid, an {@link IllegalArgumentException} is thrown.
     * <p>
     * @param breadType The desired bread type for the sandwich.
     * @throws IllegalArgumentException if the provided bread type is not among the valid options.
     */
    public void setBreadType(String breadType) {
        String normalizedBreadType = normalizeBreadType(breadType);

        if (normalizedBreadType != null) {
            this.breadType = normalizedBreadType;
            updateName();
        }else {
            throw new IllegalArgumentException(
                    "\nInvalid Bread Type: '" + breadType + "'. Valid Bread Types: ('White', 'Wheat', 'Rye', 'Wrap').\n"
            );
        }
    }

    public List<Topping> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    /**
     * Updates the name of the sandwich based on its current size and bread type.
     * The name is set only if both size and bread type are non-null.
     * The format will be: [size]" [breadType] sandwich.
     */
    private void updateName() {
        if (size != null && breadType != null) {
            setName(size + "\" " + breadType + " sandwich");
        }
    }



    /**
     * Adds a topping to the order.
     * <p>
     * If the provided {@code topping} is {@code null}, the method returns without making any changes.
     * If {@code extra} is requested ({@code true}) but the specific {@code topping} does not support
     * an extra portion (as determined by {@code topping.supportsExtra()}), a message is printed
     * to the console indicating that a regular amount will be added instead, and the {@code extra}
     * flag is effectively treated as {@code false} for this addition.
     * <p>
     * A new {@link ToppingOrder} is created with the given topping and the (potentially adjusted)
     * extra status, and then added to an internal list of toppings for the current item (e.g., a sandwich).
     *
     * @param topping The {@link Topping} to be added. Can be {@code null}, in which case nothing happens.
     * @param extra   A boolean indicating whether an extra portion of the topping is desired.
     * If {@code true} but the topping does not support extra, it will be added as non-extra.
     */
    public void addTopping(Topping topping, boolean extra) {
        if (topping == null) {
            return;
        }

        boolean extras = extra;
        if (extra && !topping.supportsExtra()) {
            System.out.println("Note: " + topping.getName() + " cannot be extra. Adding regular amount.");
            extras = false;
        }

        this.toppings.add(topping);
        this.isExtra.add(extras);
    }

    /**
     * Removes all occurrences of the specified topping from the order,
     * regardless of whether they were marked as "extra" or not.
     * <p>
     * If the provided {@code topping} is {@code null}, the method returns without
     * making any changes.
     *
     * @param topping The {@link Topping} to be removed. All orders matching this topping
     * will be removed.
     */
    public void removeTopping(Topping topping) {
        if (topping == null) {
            return;
        }

        for (int i = toppings.size() - 1; i >= 0; i--) {
            if (toppings.get(i).equals(topping)) {
                toppings.remove(i);
                isExtra.remove(i);
            }
        }
    }

    /**
     * Removes the first occurrence of a specific topping order that matches both the
     * topping type and its "extra" status.
     * <p>
     * If the provided {@code topping} is {@code null}, the method returns without
     * making any changes. If no matching {@link ToppingOrder} (considering both the topping
     * and its extra status) is found, the list of toppings remains unchanged.
     *
     * @param topping The {@link Topping} to identify the order to be removed.
     * @param extra   The "extra" status of the topping order to be removed.
     * The method will look for an order that matches both this topping
     * and this specific extra status.
     */
    public void removeTopping(Topping topping, boolean extra) {
        if (topping == null) {
            return;
        }

        for (int i = 0; i < toppings.size(); i++) {
            if (toppings.get(i).equals(topping) && isExtra.get(i) == extra) {
                toppings.remove(i);
                isExtra.remove(i);
                return;
            }
        }
    }

    /**
     * Calculates the total price of this item, fulfilling the contract from the {@link Priceable} interface.
     * The price is determined by summing the item's base price (based on its size)
     * and the total cost of all its added toppings.
     *
     * @return The total calculated price of the item as a double.
     */
    @Override
    public double getPrice() {
        return getBasePrice() + getToppingsTotal();
    }

    /**
     * Calculates the base price of the item based on its current size.
     * The size is determined by the instance variable {@code size}.
     *
     * @return The base price as a double. Returns a default price (7.00)
     * if the size does not match predefined constants.
     */
    private double getBasePrice() {
        switch (size) {
            case SIZE_FOUR -> {return 5.50;}
            case SIZE_EIGHT -> {return 7.00;}
            case SIZE_TWELVE -> {return 8.50;}
            default -> {return 7.00;}
        }
    }

    /**
     * Calculates the total price of all toppings added to this item.
     * It iterates through each {@link ToppingOrder} in the {@code toppings} list,
     * gets its price based on the current item's size ({@code this.size}),
     * and sums them up.
     *
     * @return The sum of the prices of all toppings as a double.
     */
    private double getToppingsTotal() {
        return IntStream.range(0, toppings.size())
                .mapToDouble(i -> {
                    Topping topping = toppings.get(i);
                    boolean toppingExtraStatus = isExtra.get(i);
                    return topping.getPrice(this.size, toppingExtraStatus);
                })
                .sum();
    }


}