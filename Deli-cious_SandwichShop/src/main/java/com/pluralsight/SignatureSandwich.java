package com.pluralsight;

public abstract class SignatureSandwich extends Sandwich{
    //          === Instance Variables ===
    protected String signatureName;

    protected SignatureSandwich(String signatureName, String size, String breadType) {
        super("8", "white");
        this.signatureName = signatureName;
        applySignatureToppings();
    }

    protected abstract void applySignatureToppings();
}
