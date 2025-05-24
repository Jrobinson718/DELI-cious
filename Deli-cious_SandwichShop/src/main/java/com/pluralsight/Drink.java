package com.pluralsight;

public class Drink extends MenuItem{
    //          === Constant Variables ===
    public static final String SMALL_SIZE = "Small";
    public static final String MEDIUM_SIZE = "Medium";
    public static final String LARGE_SIZE = "Large";

    //          === Instance Variables ===
    private String size;
    private String flavor;

    //          === Constructor ===

    /**
     * Constructs a new Drink with a specified size and flavor.
     * The input size is normalized. If the normalized size is invalid,
     * it defaults to MEDIUM_SIZE and a message is printed to the console.
     * The name of the drink is set based on its flavor and size.
     *
     * @param size The desired size of the drink.
     * @param flavor The flavor of the drink.
     */
    public Drink(String size, String flavor) {
        super(flavor);
        this.flavor = flavor;
        String normalizedSize = normalizeSize(size);

        if (isValidSize(normalizedSize)) {
            this.size = normalizedSize;
        }else {
            this.size = MEDIUM_SIZE;
            System.out.println("Invalid size: " + size + "\n. Applying default size (Medium).");
        }

        this.setName(this.flavor + " " + this.size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        String normalizedSize = normalizeSize(size);

        if (isValidSize(normalizedSize)) {
            this.size = normalizedSize;
            this.setName(this.flavor + " " + this.size);
        }else {
            throw new IllegalArgumentException("Invalid size: '" + size + "'. Valid sizes are 'Small', 'Medium', 'Large'");
        }
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
        this.setName(this.flavor + " " + this.size);
    }

//          === Methods ===

    /**
     * Normalizes a raw size string to its standardized form (Small, Medium, or Large).
     * Handles various common abbreviations and case variations.
     *
     * @param size The raw size string input.
     * @return The normalized size string, or the original input if not recognized as a standard variation.
     * Returns an empty string if the input is null.
     */
    private String normalizeSize (String size) {
        if (size == null) {
            return "";
        }

        String trimmedSize = size.trim().toLowerCase();

        switch (trimmedSize) {
            case "s", "sm", "sma", "smal", "small" -> {return SMALL_SIZE;}
            case "m", "med", "medi", "medium", "mid" -> {return MEDIUM_SIZE;}
            case "l", "lg", "lar", "larg", "large" -> {return LARGE_SIZE;}
            default -> {return size;}
        }
    }

    /**
     * Checks if a given size string is one of the valid, standardized sizes.
     *
     * @param size The size string to validate (should be pre-normalized).
     * @return {@code true} if the size is valid (Small, Medium, or Large), {@code false} otherwise.
     */
    private boolean isValidSize(String size){
        return size.trim().equalsIgnoreCase(SMALL_SIZE) ||
                size.trim().equalsIgnoreCase(MEDIUM_SIZE) ||
                size.trim().equalsIgnoreCase(LARGE_SIZE);
    }

    /**
     * Calculates the price of the drink based on its current size.
     *
     * @return The price of the drink. Defaults to the medium price if the size is somehow unrecognized.
     */
    @Override
    public double getPrice() {
        switch (this.size) {
            case SMALL_SIZE -> {return 2.00;}
            case MEDIUM_SIZE -> {return 2.50;}
            case LARGE_SIZE -> {return 3.00;}
            default -> {return 2.50;}
        }
    }
}
