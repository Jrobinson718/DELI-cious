# DELI-cious: A Command-Line Deli Order Application

## Project Overview

DELI-cious is a console-based Java application designed to simulate a deli ordering system. Users can build custom sandwiches, choose from signature sandwiches, add drinks and chips, manage their order, and proceed to a simple checkout process where a receipt is generated and saved.

The project is structured using Object-Oriented Programming (OOP) principles, emphasizing clear separation of concerns, inheritance, and polymorphism to model real-world deli items and their interactions.

## Features

* **Main Menu Navigation:** Start a new order, pick a signature sandwich, or exit the application.
* **Custom Sandwich Builder:**
    * Select bread type (White, Wheat, Rye, Wrap) and size (4", 8", 12").
    * Add a variety of toppings, including meats, cheeses, regular vegetables, and sauces.
    * **Manual "Extra" Selection:** When adding toppings, you explicitly specify if an extra portion is desired. The system will notify you if a topping does not support an "extra" portion.
    * Choose to toast the sandwich.
* **Signature Sandwiches:** Pre-defined sandwiches (e.g., BLT, Philly Cheese Steak) with fixed toppings and settings.
* **Drinks & Chips:** Add drinks with various sizes and flavors, and various types of chips.
* **Order Management:**
    * View current order details, including item names, prices, and sandwich specifics (toppings, toasted status).
    * Remove items from the order.
    * Modify existing sandwiches in the order (re-enter sandwich builder for an existing sandwich).
    * Cancel the entire order.
* **Checkout Process:**
    * Display final order total.
    * Simulated "cash" payment.
    * Generates and saves a detailed receipt to a `Receipts` folder.

## Project Structure and Key Classes

The application is built around several core classes, each responsible for a specific aspect of the deli system:

### Core Data / Model Classes

* `MenuItem.java`: An `abstract` class representing any item that can be part of an order. It implements `Priceable` and has a `name` property.
    * `getPrice()`: Abstract method that must be implemented by subclasses to return the item's price.
* `Priceable.java`: An `interface` defining the `getPrice()` method, ensuring all priceable items can return their cost.
* `Bread.java`: Represents the bread component of a sandwich, including size and type. It handles price calculation for the base bread.
* `Topping.java`: Represents a sandwich topping. It includes properties like `name`, `category` (Meat, Cheese, Regular, Sauce, Side), and `isPremium`. It also contains static maps for available toppings and methods to calculate topping prices based on size and whether it's "extra."
    * **Important: Overrides `equals()` and `hashCode()`** methods, which is a good practice for object comparison, even if not directly used for auto-extra logic in `Sandwich`.
* `Sandwich.java`: Extends `MenuItem` and represents a custom-built sandwich. It manages a `List` of `Topping` objects and a corresponding `List<Boolean>` (`isExtra`) to track if each topping is an "extra" portion.
    * **`addTopping(Topping topping, boolean extra)`:** This method allows adding a topping and explicitly specifying if it's an "extra" portion. It includes a check to ensure only premium toppings (meat/cheese) can be "extra."
    * **Example: `Sandwich` `addTopping()` method**
        *(![Screenshot 2025-05-29 183931](https://github.com/user-attachments/assets/477fa65a-1829-4de9-aace-62a8249aa2e0)
      
place `assets/sandwich_add_topping.png` with the actual path to your screenshot)*
    * `removeTopping(Topping topping)`: Removes all instances of a specific topping.
    * `removeTopping(Topping topping, boolean extra)`: Removes the first instance of a specific topping with a matching "extra" status.
* `SignatureSandwich.java`: An `abstract` class that extends `Sandwich`. It serves as a template for predefined sandwiches with specific ingredient sets.
    * `applySignatureToppings()`: An `abstract` method that concrete `SignatureSandwich` subclasses must implement to define their unique toppings and configurations.
    * **Example: `SignatureSandwich` Constructor**
    * 
* `BLT.java`: A concrete implementation of `SignatureSandwich`, defining the standard BLT sandwich.
* `PhillyCheeseSteak.java`: A concrete implementation of `SignatureSandwich`, defining the standard Philly Cheese Steak sandwich.
* `Drink.java`: Extends `MenuItem` to represent a drink, handling size-based pricing.
* `Chips.java`: Extends `MenuItem` to represent a bag of chips, with a fixed price.
* `Order.java`: Manages the collection of `MenuItem` objects for a single customer's order. It can add/remove items, calculate the total price, clear the order, and generate a detailed receipt format.
    * **Example: `Order` `generateReceiptFormat()` **
        ![Order generateReceiptFormat method](![Screenshot 2025-05-29 184210](https://github.com/user-attachments/assets/d9d45168-7b78-4f8f-af0d-6270b771d51a)
)

* `Receipt.java`: Handles the generation and saving of order receipts to a dedicated `Receipts` folder. It uses `LocalDateTime` for timestamping receipt filenames.

### User Interface / Flow Classes

* `Console.java`: A utility class for handling user input from the console. It provides robust methods for prompting for `int`, `long`, `float`, and `String` inputs, including basic error handling for invalid input types.
    * **Example: `Console` `promptForInt()` method**

* `HomeScreen.java`: The main entry point of the application. It displays the primary menu and orchestrates navigation to other screens. It holds the `currentOrder` object for the session.
* `OrderScreen.java`: Manages the display and modification of the current order. It provides options to add custom sandwiches, drinks, chips, remove items, modify sandwiches, and proceed to checkout or cancel.
* `SandwichBuilderScreen.java`: A dedicated screen for building custom sandwiches or modifying existing ones. It guides the user through bread selection, topping additions/removals, and toasting options.
* `CheckoutScreen.java`: Handles the final checkout process, displaying the total, simulating payment, generating a receipt, and clearing the order.
* `DeliMenu.java`: A utility class that stores and provides access to pre-defined `SignatureSandwich` objects.

## UML Class Diagram

This diagram illustrates the relationships, inheritance, and interfaces within the DELI-cious project, providing a high-level overview of its architecture.

![UML Class Diagram](![Editor _ Mermaid Chart-2025-05-29-231613](https://github.com/user-attachments/assets/732e2002-74a9-4c90-9691-4d4477ba30c4)

## How to Run the Application

1.  **Compile:** Navigate to the `src` directory (or wherever your `com` folder is located) in your terminal and compile all Java files:
    ```bash
    javac com/pluralsight/*.java
    ```
    (Note: If you are using an IDE like IntelliJ or Eclipse, it will handle compilation automatically.)

2.  **Run:** From the directory *containing* the `com` folder, execute the `HomeScreen` class:
    ```bash
    java com.pluralsight.HomeScreen
    ```

3.  **Interact:** Follow the on-screen prompts in the console to navigate the menu, build orders, and checkout.

## Future Enhancements (Potential Ideas)

* **Payment Options:** Expand payment methods beyond cash (e.g., card simulation).
* **User Accounts:** Implement basic user authentication and order history.
* **Inventory Management:** Track available ingredients and prevent ordering out-of-stock items.
* **Graphical User Interface (GUI):** Convert the console application to a GUI using Swing, JavaFX, or a web framework.
* **Advanced Order Customization:** Allow specific quantities for regular toppings.
* **Discount System:** Implement promotions or loyalty programs.
* **Error Handling Refinements:** More specific and user-friendly error messages for various invalid inputs.
