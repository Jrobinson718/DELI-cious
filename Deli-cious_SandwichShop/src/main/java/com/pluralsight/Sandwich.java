package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Sandwich extends MenuItem implements Priceable{
    //          === Instance Variables ===
    private Bread bread;
    private List<Topping> toppings;
    private List<Boolean> isExtra;
    private boolean isToasted;

    //          === Constructor ===
    public Sandwich(String size, String breadType) {
        super("Sandwich");
        this.toppings = new ArrayList<>();
        this.isExtra = new ArrayList<>();
        this.isToasted = false;
        this.bread = new Bread(size, breadType);
        updateName();
    }

    //          === Getters ===

    public boolean isToasted() {
        return isToasted;
    }

    public String getSize() {
        return bread.getSize();
    }

    public String getBreadType() {
        return bread.getBreadType();
    }

    public List<Topping> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    private void updateName() {
        if (this.bread != null) {
            setName(bread.getDisplayName() + " sandwich");
        }
    }

    public List<Boolean> getToppingExtraStatuses() {
        return Collections.unmodifiableList(this.isExtra);
    }

    //          === Methods ===
    /**
     * Adds a topping to the sandwich.
     * <p>
     * If the {@code topping} is {@code null}, the method returns without making any changes.
     * If {@code extra} is requested ({@code true}) but the specific {@code topping} does not support
     * an extra portion (determined by {@code topping.supportsExtra()}), a message is printed
     * to the console, and the topping is added as a regular amount.
     * <p>
     * The topping and its determined "extra" status are added to their own
     * internal lists, ensuring proper management.
     *
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
     * Removes the first occurrence of a specific topping from the sandwich that matches
     * both the topping type and its "extra" status.
     * <p>
     * If the {@code topping} is {@code null}, the method returns without
     * making any changes. If no matching topping is found, the lists of toppings remain unchanged.
     *
     * @param topping The {@link Topping} to identify the entry to be removed.
     * @param extra   The "extra" status of the topping entry to be removed.
     * The method will look for the first entry that matches both this topping
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
        return bread.getBasePrice() + getToppingsTotal();
    }


    /**
     * Calculates the total price of all toppings added to this sandwich.
     * It iterates through each topping and its corresponding "extra" status,
     * getting its price based on the current sandwich's size using a stream.
     *
     * @return The sum of the prices of all toppings as a double.
     */
    private double getToppingsTotal() {
        return IntStream.range(0, toppings.size())
                .mapToDouble(i -> {
                    Topping topping = toppings.get(i);
                    boolean toppingExtraStatus = isExtra.get(i);
                    return topping.getPrice(this.bread.getSize(), toppingExtraStatus);
                })
                .sum();
    }
}