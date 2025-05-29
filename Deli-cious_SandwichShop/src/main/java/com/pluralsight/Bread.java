package com.pluralsight;

import java.util.Map;

public class Bread {
    //          === Constant Variables ===
    public static final String SIZE_FOUR = "4";
    public static final String SIZE_EIGHT = "8";
    public static final String SIZE_TWELVE = "12";
    public static final String BREAD_WHITE = "White";
    public static final String BREAD_WHEAT = "Wheat";
    public static final String BREAD_RYE = "Rye";
    public static final String BREAD_WRAP = "Wrap";

    //          === Instance Variables ===
    private String breadType;
    private String size;

    //          === Constructor ===
    public Bread(String sizeInput, String typeInput) {
        this.size = normalizeSize(sizeInput);
        if (this.size == null) {
            throw new IllegalArgumentException(
                    "\nInvalid size: '" + sizeInput + "'. Valid sizes include: 4, 8, 12, 'Small', 'Medium', 'Large').\n"
            );
        }

        this.breadType = normalizeBreadType(typeInput);
        if (this.breadType == null) {
            throw new IllegalArgumentException(
                    "\nInvalid Bread Type: '" + typeInput + "'. Valid Bread Types: ('White', 'Wheat', 'Rye', 'Wrap').\n"
            );
        }
    }

    /**
     * A static, unmodifiable map that defines various common string inputs for sandwich sizes
     * and maps them to a standardized size constant (@code SIZE_FOUR}, {@code SIZE_EIGHT}, {@code SIZE_TWELVE}).
     * This map is used by the {@code normalizeSize} method to convert user input
     * (like "4", "small", "s") into a consistent internal representation.
     * The keys are the lowercase string variations, and the values are the standardized size strings.
     */
    private static final Map<String, String> SIZE_VARIATIONS = Map.ofEntries(
            Map.entry("4", SIZE_FOUR), Map.entry("4\"", SIZE_FOUR),
            Map.entry("4 inch", SIZE_FOUR), Map.entry("four", SIZE_FOUR),
            Map.entry("small", SIZE_FOUR), Map.entry("s", SIZE_FOUR),
            Map.entry("sm", SIZE_FOUR),

            Map.entry("8", SIZE_EIGHT), Map.entry("8\"", SIZE_EIGHT),
            Map.entry("8 inch", SIZE_EIGHT), Map.entry("eight", SIZE_EIGHT),
            Map.entry("medium", SIZE_EIGHT), Map.entry("m", SIZE_EIGHT),
            Map.entry("med", SIZE_EIGHT), Map.entry("mid", SIZE_EIGHT),

            Map.entry("12", SIZE_TWELVE), Map.entry("12\"", SIZE_TWELVE),
            Map.entry("12 inch", SIZE_TWELVE), Map.entry("twelve", SIZE_TWELVE),
            Map.entry("large", SIZE_TWELVE), Map.entry("l", SIZE_TWELVE),
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
            Map.entry("white", BREAD_WHITE), Map.entry("w", BREAD_WHITE),
            Map.entry("wheat", BREAD_WHEAT), Map.entry("wh", BREAD_WHEAT),
            Map.entry("whole wheat", BREAD_WHEAT), Map.entry("rye", BREAD_RYE),
            Map.entry("r", BREAD_RYE), Map.entry("wrap", BREAD_WRAP),
            Map.entry("tortilla", BREAD_WRAP)
    );

    //          === Normalizing methods ===
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

        return SIZE_VARIATIONS.get(input.trim().toLowerCase());
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

        return BREAD_VARIATIONS.get(input.trim().toLowerCase());
    }

    //          === Getters ===

    public String getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getDisplayName() {
        return size + "\" " + breadType;
    }

    /**
     * Calculates the base price of the item based on its current size.
     * The size is determined by the instance variable {@code size}.
     *
     * @return The base price as a double. Returns a default price (7.00)
     * if the size does not match predefined constants.
     */
    public double getBasePrice() {
        switch (size) {
            case SIZE_FOUR -> {return 5.50;}
            case SIZE_EIGHT -> {return 7.00;}
            case SIZE_TWELVE -> {return 8.50;}
            default -> {return 7.00;}
        }
    }
}