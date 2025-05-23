package com.pluralsight;

public class Chips extends MenuItem{

    public Chips(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
}
