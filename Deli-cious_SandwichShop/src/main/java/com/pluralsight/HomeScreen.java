package com.pluralsight;

import java.util.Map;

/**
 * The main entry point and control hub for the DELI-cious application.
 * This class manages the overall application flow, displays the main menu,
 * and orchestrates navigation between different screens (Order, Checkout, Sandwich Builder).
 * It holds the single {@link Order} object for the current customer session.
 */
public class HomeScreen {
    //          === Instance Variables ===

    private Console console;
    private OrderScreen orderScreen;
    private SandwichBuilderScreen sandwichBuilderScreen;
    private CheckoutScreen checkoutScreen;
    private Order currentOrder;

    public HomeScreen() {
        this.console = new Console();
        this.currentOrder = new Order();
        this.sandwichBuilderScreen = new SandwichBuilderScreen(console);
        this.checkoutScreen = new CheckoutScreen(console);
        this.orderScreen = new OrderScreen(console, currentOrder, sandwichBuilderScreen, checkoutScreen);
    }

    /**
     * Displays the main menu of the DELI-cious application and manages the primary application loop.
     * Users can choose to start a new custom order, pick a signature sandwich, or exit the application.
     * The loop continues until the user explicitly chooses to exit.
     */
    public void display() {
        int choice;
        do {
            System.out.println("\n=== DELI-cious Home Screen ===" +
                    "\n1) New Order (Custom Sandwich)" +
                    "\n2) Pick Signature Sandwich" +
                    "\n0) Exit");

            choice = console.promptForInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    currentOrder.clearOrder();
                    System.out.println("\n--- Starting New Custom Order ---");
                    orderScreen.displayOrderScreen();
                    break;
                case 2:
                    handlePickSignatureSandwich();
                    if (!currentOrder.getOrderItems().isEmpty()) {
                        System.out.println("\n--- Proceeding to Order Screen ---");
                        orderScreen.displayOrderScreen();
                    }
                    break;
                case 0:
                    System.out.println("Thanks for coming to the sandwich shop. Have a DELI-cious day!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while (choice != 0);
    }

    /**
     * Manages the user interaction for selecting and adding a signature sandwich to the current order.
     * It retrieves available signature sandwiches from {@link DeliMenu}, displays them,
     * prompts the user for a selection, and adds a copy of the selected sandwich to the order.
     * If a signature sandwich is added, the user is then taken to the {@link OrderScreen}.
     */
    private void handlePickSignatureSandwich() {
        Map<Integer, Sandwich> signatureSandwiches = DeliMenu.getSignatureSandwiches();

        if (signatureSandwiches.isEmpty()) {
            System.out.println("Sorry we currently don't have any signature sandwiches at this time :(.");
            return;
        }

        System.out.println("\n--- Pick a Signature Sandwich ---");
        signatureSandwiches.forEach((key, sandwich) ->
                System.out.printf("%d) %s ($%.2f)%n", key, sandwich.getName(), sandwich.getPrice()));
        System.out.println("0) Back to Home Screen");

        int selection = console.promptForInt("Enter the number of the signature sandwich you want: ");

        if (selection == 0) {
            System.out.println("No signature sandwich added.");
            return;
        }

        Sandwich selectedSignature = signatureSandwiches.get(selection);

        if (selectedSignature != null) {
            if (currentOrder.getOrderItems().isEmpty()) {
                currentOrder.clearOrder();
            }

            Sandwich signatureToAdd = createSandwichCopy(selectedSignature);

            currentOrder.addItem(signatureToAdd);
            System.out.printf("'%s' Added to your order!", signatureToAdd.getName());
        }
    }

    /**
     * Creates a copy of a {@link Sandwich} object.
     * This method is crucial when adding a signature sandwich from {@link DeliMenu} to an order.
     * It ensures that any modifications made by the user to their sandwich in the order
     * do not affect the original signature sandwich in {@link DeliMenu}.
     * <p>
     * The method creates a new generic {@code Sandwich} instance and copies all relevant attributes
     * (size, bread type, name, toasted status, and individual toppings) from the original
     * {@code Sandwich} object. The returned copy will have the same functional properties
     * but will be a distinct object of type {@code Sandwich}).
     * </p>
     *
     * @param original The original {@code Sandwich} object to copy.
     * @return A new {@code Sandwich} instance with identical properties to the original,
     * but independent of the original template.
     */
    private Sandwich createSandwichCopy(Sandwich original) {
        Sandwich copy = new Sandwich(original.getSize(), original.getBreadType());
        copy.setName(original.getName());
        copy.setToasted(original.isToasted());

        for (int i = 0; i < original.getToppings().size(); i++) {
            copy.addTopping(original.getToppings().get(i), original.getToppingExtraStatuses().get(i));
        }
        return copy;
    }
}