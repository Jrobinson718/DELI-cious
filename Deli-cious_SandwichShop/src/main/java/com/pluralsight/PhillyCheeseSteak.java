package com.pluralsight;

public class PhillyCheeseSteak extends SignatureSandwich{

    public PhillyCheeseSteak() {
        super("Philly Cheese Steak", "8", "White");
    }

    @Override
    protected void applySignatureToppings(){
        PremiumTopping steak = new PremiumTopping("Steak", PremiumTopping.MEAT, 1.00, .50);
        PremiumTopping americanCheese = new PremiumTopping("American Cheese", PremiumTopping.CHEESE, .75, .30);
        RegularTopping peppers = new RegularTopping("Peppers", RegularTopping.REGULAR);
        RegularTopping mayo = new RegularTopping("Mayo", RegularTopping.SAUCE);

        this.addTopping(steak, false);
        this.addTopping(americanCheese, false);
        this.addTopping(peppers, false);
        this.addTopping(mayo, false);
        this.setToasted(true);
    }
}
