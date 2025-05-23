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

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name + " -$" + String.format("%.2f", getPrice()) + "\n";
    }
}
