package com.pluralsight;

public class CheckoutScreen {
    //          === Instance Variables ===

    private Console console;

    //          === Constructor ===

    public CheckoutScreen(Console console) {
        this.console = console;
    }

    //          === Methods ===

    /**
     * Displays the final order details and guides the user through a checkout process.
     * After successful "payment", it clears the order.
     *
     * @param order The current Order object containing all items to be checked out.
     */
    public void displayCheckout(Order order) {
        System.out.println("\n--- Proceeding to Checkout ---");

        System.out.println(order.generateReceiptFormat());

        double total = order.getTotalPrice();
        System.out.printf("%nYour final total is: $%.2f%n", total);

        System.out.println("\n--- Payment ---" +
                "\nPayment methods accepted: Cash (enter 'cash')");
        String paymentMethod = console.promptForString("Enter payment method: ");

        if (paymentMethod.equalsIgnoreCase("cash")){
            System.out.println("Payment received. Processing your order...");
            System.out.println("Payment successful! Thank you for your order.");

            Receipt receipt = new Receipt(order);
            receipt.saveToFile();

            order.clearOrder();
            System.out.println("Order completed. Enjoy your meal!");
        }else {
            System.out.println("Payment method not recognized or funds insufficient. Please try again.");
        }
        console.promptForString("Press Enter to return to main menu...");
    }
}
