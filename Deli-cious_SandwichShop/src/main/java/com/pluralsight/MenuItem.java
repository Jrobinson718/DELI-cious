package com.pluralsight;

public abstract class MenuItem implements Priceable{
    //          === Instance Variables ===
    protected String name;

    //          === Constructor ===
    protected MenuItem(String name) {
        this.name = name;
    }

    //          === Getter for name ===
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name + " -$" + getPrice();
    }
}
