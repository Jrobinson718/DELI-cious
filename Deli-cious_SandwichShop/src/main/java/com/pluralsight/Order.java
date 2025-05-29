package com.pluralsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    //          === Instance Variables ===
    private List<MenuItem> orderItems;

    //          === Constructor ===
    public Order() {
        this.orderItems = new ArrayList<>();
    }

    //          === Methods ===
    /**
     * Adds a {@link MenuItem} to this order.
     *
     * @param item The {@link MenuItem} to be added to the order. Must not be null.
     */
    public void addItem(MenuItem item) {
        orderItems.add(item);
    }

    /**
     * Removes a specific {@link MenuItem} from this order.
     * If the item is not found in the order, the order remains unchanged.
     *
     * @param item The {@link MenuItem} to be removed from the order.
     */
    public void removeItem(MenuItem item) {
        orderItems.remove(item);
    }

    /**
     * Calculates the total price of all {@link MenuItem}s currently in the order.
     *
     * @return The sum of the prices of all items in the order as a double.
     */
    public double getTotalPrice() {
        return orderItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }

    public void clearOrder() {
        this.orderItems.clear();
    }

    /**
     * Returns an unmodifiable view of the list of {@link MenuItem}s in this order.
     * This prevents direct modification of the order's item list from outside the class.
     *
     * @return An unmodifiable {@link List} of {@link MenuItem}s.
     */
    public List<MenuItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    /**
     * Generates a formatted string representation of the order for a receipt.
     * The receipt includes a header, details for each item, and the total order price.
     * <p>
     * For each {@link MenuItem}, its name and price are listed.
     * If an item is a {@link Sandwich}, its toasted status (if applicable) is detailed,
     * followed by a list of its individual toppings. For each topping, its name is included,
     * and if it was added as an "extra" portion, that is also noted.
     * The topping details are retrieved by accessing the sandwich's list of toppings
     * and a list of boolean statuses to see if each topping is extra.
     * </p>
     *
     * @return A string formatted as a receipt for the current order.
     */
    public String generateReceiptFormat() {
        StringBuilder receipt = new StringBuilder();

        receipt.append("Order Details:\n");
        receipt.append("-----------------\n");

        for (MenuItem item : orderItems) {
            receipt.append(String.format("%n%s - $%.2f%n", item.getName(), item.getPrice()));

            if (item instanceof Sandwich sandwich) {
                if (sandwich.isToasted()) {
                    receipt.append("  * Toasted\n");
                }

                List<Topping> toppings = sandwich.getToppings();
                List<Boolean> extraStatus = sandwich.getToppingExtraStatuses();

                for (int i = 0; i < toppings.size(); i++) {
                    Topping topping = toppings.get(i);
                    boolean isExtra = extraStatus.get(i);
                    String toppingName = topping.getName();
                    if (isExtra){
                        receipt.append(String.format("  + Extra %s%n", toppingName));
                    }else {
                        receipt.append(String.format("  + %s%n", toppingName));
                    }
                }
            }
        }
        receipt.append("-----------------\n");
        receipt.append(String.format("Total: $%.2f%n", getTotalPrice()));

        return receipt.toString();
    }
}
