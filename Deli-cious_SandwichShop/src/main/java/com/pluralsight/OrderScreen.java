package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class OrderScreen {
    //          === Instance Variables ===

    private Console console;
    private Order currentOrder;
    private SandwichBuilderScreen sandwichBuilderScreen;
    private CheckoutScreen checkoutScreen;

    //          === Constructor ===

    public OrderScreen(Console console, Order currentOrder, SandwichBuilderScreen sandwichBuilderScreen, CheckoutScreen checkoutScreen) {
        this.console = console;
        this.currentOrder = currentOrder;
        this.sandwichBuilderScreen = sandwichBuilderScreen;
        this.checkoutScreen = checkoutScreen;
    }

    //          === Methods ===

    /**
     * Displays the order management menu and allows the user to add/remove/modify items,
     * or proceed to checkout/cancel the order.
     * This method assumes 'currentOrder' might already contain items like a signature sandwich.
     */
    public void displayOrderScreen() {
        int choice;
        do {
            displayCurrentOrder();
            System.out.println("\n--- Order Screen ---" +
                    "\n1) Add sandich (Custom)" +
                    "\n2) Add Drink" +
                    "\n3) Add Chips" +
                    "\n4) Remove Item" +
                    "\n5) Modify Sandwich" +
                    "\n6) Checkout" +
                    "\n0) Cancel order (Back to Home)");

            choice = console.promptForInt("Enter your choice: ");

            switch (choice) {
                case 1: handleAddSandwich(); break;
                case 2: handleAddDrink(); break;
                case 3: handleAddChips(); break;
                case 4: handleRemoveItem(); break;
                case 5: handleModifySandwich(); break;
                case 6: handleCheckout(); break;
                case 0: handleCancelOrder(); break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while (choice != 7 && choice != 0);
    }

    /**
     * Displays the current items in the order, or a message if the order is empty.
     */
    private void displayCurrentOrder() {
        if (currentOrder.getOrderItems().isEmpty()) {
            System.out.println("\n--- Your Order is Currently Empty ---");
        }else {
            System.out.println("\n--- Your Current Order ---");
            System.out.println(currentOrder.generateReceiptFormat());
        }
    }

    /**
     * Guides the user through the process of adding a custom sandwich to the order.
     * Utilizes the SandwichBuilderScreen to configure the sandwich.
     * Allows adding multiple custom sandwiches.
     */
    private void handleAddSandwich() {
        String addAnother = "yes";
        while (addAnother.equalsIgnoreCase("yes")) {
            Sandwich newSandwich = sandwichBuilderScreen.buildSandwich();
            currentOrder.addItem(newSandwich);
            System.out.println("Sandwich '" + newSandwich.getName() + "' added to your order!");

            addAnother = console.promptForString("Would you like to add another custom sandwich? (Yes/No): ");
        }
    }

    /**
     * Guides the user through adding a drink to the current order.
     * Prompts for drink flavor and size, using the Drink class's
     * validation and pricing logic. Allows adding multiple drinks.
     */
    private void handleAddDrink() {
        System.out.println("\n--- Add Drink ---");

        String flavor = console.promptForString("Please enter the drink flavor: ");
        System.out.println("Available sized: " + Drink.SMALL_SIZE + ", " + Drink.MEDIUM_SIZE + ", " + Drink.LARGE_SIZE);
        String size = console.promptForString("Enter drink size ('Small', 'Medium', 'Large' or abbreviations): ");

        try {
            Drink newDrink = new Drink(size, flavor);
            currentOrder.addItem(newDrink);
            System.out.printf("'%s' added to your order for $%.2f.%n", newDrink.getName(), newDrink.getPrice());
        }catch (IllegalArgumentException e) {
            System.out.println("Error adding drink: " + e.getMessage());
        }
    }

    /**
     * Guides the user through adding chips to the current order.
     * Chips are always priced at $1.50 regardless of the specific type/flavor.
     * Allows adding multiple bags of chips.
     */
    private void handleAddChips() {
        String addAnother = "yes";
        while (addAnother.equalsIgnoreCase("yes")) {
            System.out.println("\n--- Add Chips ---");

            String chipType = console.promptForString("What chips would you like: ");
            Chips newChips = new Chips(chipType);

            currentOrder.addItem(newChips);
            System.out.printf("'%s' added to your order for $%.2f.%n", newChips.getName(), newChips.getPrice());

            addAnother = console.promptForString("Add another bag of chips? (Yes/No): ");
        }
    }

    /**
     * Allows the user to remove an item from the current order.
     * Displays a numbered list of items and prompts for selection.
     */
    private void handleRemoveItem() {
        if (currentOrder.getOrderItems().isEmpty()) {
            System.out.println("Your order is empty. Nothing to remove.");
            return;
        }

        System.out.println("\n--- Remove Item ---");
        List<MenuItem> items = currentOrder.getOrderItems();
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.printf("%d) %s - $%.2f%n", (i + 1), item.getName(), item.getPrice());
        }

        System.out.println("0) Go back");

        int selection = console.promptForInt("Enter the number of the item you want to remove: ");
        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= items.size()) {
            MenuItem itemToRemove = items.get(selection - 1);
            currentOrder.removeItem(itemToRemove);
            System.out.printf("%n'%s' removed from order.%n", itemToRemove.getName());
        }else {
            System.out.println("Invalid selection. Please enter a number from the list.");
        }
    }

    /**
     * Allows the user to modify an existing sandwich in the order.
     * Displays a numbered list of sandwiches and prompts for selection,
     * then delegates to SandwichBuilderScreen for modification.
     */
    private void handleModifySandwich() {
        if (currentOrder.getOrderItems().isEmpty()) {
            System.out.println("Your order is empty. Nothing to modify.");
            return;
        }

        System.out.println("\n--- Select Sandwich to Modify ---");
        List<MenuItem> items = currentOrder.getOrderItems();
        List<Integer> sandwichIndices = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Sandwich) {
                sandwichIndices.add(i);
                System.out.printf("%d) %s - $%.2f%n", sandwichIndices.size(), items.get(i).getName(), items.get(i).getPrice());
            }
        }

        if (sandwichIndices.isEmpty()) {
            System.out.println("No sandwiches in your order to modify.");
            return;
        }

        System.out.println("0) Go back");

        int selection = console.promptForInt("Enter the number of the sandwich you want to modify: ");
        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= sandwichIndices.size()) {
            int actualIndex = sandwichIndices.get(selection - 1);
            MenuItem selectedItem = items.get(actualIndex);

            Sandwich sandwichToModify = (Sandwich) selectedItem;

            sandwichBuilderScreen.modifySandwich(sandwichToModify);
            System.out.println("Sandwich '" + sandwichToModify.getName() + "' has been modified.");
        }else {
            System.out.println("Invalid selection. Please enter a valid number from the list.");
        }
    }

    /**
     * Initiates the checkout process for the current order by going to the CheckoutScreen.
     * Prevents checkout if the order is empty.
     */
    private void handleCheckout() {
        if (currentOrder.getOrderItems().isEmpty()) {
            System.out.println("Your order is empty. Cannot proceed to checkout.");
            return;
        }
        System.out.println("Proceeding to checkout...");
        checkoutScreen.displayCheckout(currentOrder);
    }

    /**
     * Handles the cancellation of the current order.
     * Prompts for confirmation and clears the order if confirmed, then returns to the home screen.
     */
    private void handleCancelOrder() {
        String confirm = console.promptForString("Are you sure you want to cancel the order? All items will be removed. (Yes/No): ");
        if (confirm.equalsIgnoreCase("yes")) {
            currentOrder.clearOrder();
            System.out.println("Order cancelled. Returning to the home screen.");
        }else {
            System.out.println("Order not cancelled. Returning to order screen.");
        }
    }
}
