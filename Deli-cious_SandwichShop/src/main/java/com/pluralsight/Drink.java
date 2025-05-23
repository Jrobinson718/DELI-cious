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
    public Drink(String rawSize, String flavor) {
        super(flavor);
        this.flavor = flavor;
        String normalizedSize = normalizeSize(rawSize);

        if (isValidSize(normalizedSize)) {
            this.size = normalizedSize;
        }else {
            this.size = MEDIUM_SIZE;
            System.out.println("Invalid size: " + rawSize + "\n. Applying default size (Medium).");
        }

        this.setName(this.flavor + " " + this.size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String rawSize) {
        String normalizedSize = normalizeSize(rawSize);

        if (isValidSize(normalizedSize)) {
            this.size = normalizedSize;
            this.setName(this.flavor + " " + this.size);
        }else {
            throw new IllegalArgumentException("Invalid size: '" + rawSize + "'. Valid sizes are 'Small', 'Medium', 'Large'");
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

    // Method to take possible different ways of asking for a size and changing it to the correct size
    private String normalizeSize (String rawSize) {
        if (rawSize == null) {
            return "";
        }

        String trimmedSize = rawSize.trim().toLowerCase();

        switch (trimmedSize) {
            case "s", "sm", "sma", "smal", "small" -> {return SMALL_SIZE;}
            case "m", "med", "medi", "medium", "mid" -> {return MEDIUM_SIZE;}
            case "l", "lg", "lar", "larg", "large" -> {return LARGE_SIZE;}
            default -> {return rawSize;}
        }
    }

    // Helper method to validate the size
    private boolean isValidSize(String size){
        return size.trim().equalsIgnoreCase(SMALL_SIZE) ||
                size.trim().equalsIgnoreCase(MEDIUM_SIZE) ||
                size.trim().equalsIgnoreCase(LARGE_SIZE);
    }

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
