package com.pluralsight;

public class BLT extends SignatureSandwich{

    public BLT() {
        super("BLT", "8", "white");
    }

    @Override
    protected void applySignatureToppings() {
        PremiumTopping bacon = new PremiumTopping("Bacon", PremiumTopping.MEAT, 1.00, 0.50);
        PremiumTopping cheddar = new PremiumTopping("Cheddar", PremiumTopping.CHEESE, .75, .30);
        RegularTopping lettuce = new RegularTopping("Lettuce", RegularTopping.REGULAR);
        RegularTopping tomato = new RegularTopping("Tomato", RegularTopping.REGULAR);
        RegularTopping ranch = new RegularTopping("Ranch", RegularTopping.SAUCE);

        this.addTopping(bacon, false);
        this.addTopping(cheddar, false);
        this.addTopping(lettuce, false);
        this.addTopping(tomato, false);
        this.addTopping(ranch, false);

        this.setToasted(true);
    }
}

