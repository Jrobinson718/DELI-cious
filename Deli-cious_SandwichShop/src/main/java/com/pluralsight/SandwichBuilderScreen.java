package com.pluralsight;

import java.util.List;
import java.util.Map;

public class SandwichBuilderScreen {
    //          === Instance Variables ===
    private Console console;

    //          === Constructor ===
    public SandwichBuilderScreen(Console console) {
        this.console = console;
    }

    //          === Methods ===
    /**
     * Guides the user through building a brand new custom sandwich.
     * This method handles selecting the base bread and size, adding toppings,
     * and setting the toasted status. It also enforces limits on "extra" meat and cheese.
     *
     * @return A fully configured and new Sandwich object based on user input.
     */
    public Sandwich buildSandwich() {
        System.out.println("\n=== Build Your Custom Sandwich ===");

        Sandwich sandwich = null;
        while (sandwich == null) {
            try {
                String sizeInput = console.promptForString("Enter sandwich size." +
                        "Available sizes include (4\", 8\", 12\"): ");
                String breadTypeInput = console.promptForString("Enter bread type." +
                        "\nAvailable bread types include (White, Wheat, Rye, Wrap): ");
                sandwich = new Sandwich(sizeInput, breadTypeInput);
                System.out.println("Starting your " + sandwich.getName() + ".");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        addToppingsToSandwich(sandwich);
        promptForToasting(sandwich);
        return sandwich;
    }

    /**
     * Allows the user to modify an existing Sandwich object.
     * Presents options to add/remove toppings.
     *
     * @param sandwichToModify The existing Sandwich object to be modified.
     * Changes are applied directly to this object.
     */
    public void modifySandwich (Sandwich sandwichToModify) {
        System.out.println("\n--- Modifying Your Sandwich ---");
        displaySandwichDetails(sandwichToModify);

        String choice;
        do {
            System.out.println("\n---Modification Options ---" +
                    "\n1) Add Topping" +
                    "\n2) Remove Topping" +
                    "\n0) Done Modifying!");

            int option = console.promptForInt("Choose an option: ");
            switch (option) {
                case 1: addToppingsToSandwich(sandwichToModify); break;
                case 2: removeToppingFromSandwich(sandwichToModify); break;
                case 0: System.out.println("Your changes have been saved!"); return;
                default: System.out.println("Invalid option. Please try again."); break;
            }
            displaySandwichDetails(sandwichToModify);
            choice = console.promptForString("Continue modifying? (Yes/No): ");
        }while (choice.equalsIgnoreCase("yes"));
    }

    /**
     * Guides the user through adding toppings to the specified sandwich.
     * This method implements the specific rules for limiting "extra" meat (max 2 extra)
     * and "extra" cheese (max 3 extra) portions per individual topping type.
     *
     * @param sandwich The Sandwich object to which toppings will be added.
     */
    private void addToppingsToSandwich(Sandwich sandwich) {
        String addMoreChoice = null;
        do {
            System.out.println("\n--- Add Toppings ---" +
                    "\n1) Meat" +
                    "\n2) Cheese" +
                    "\n3) Regular Toppings" +
                    "\n4) Sauces" +
                    "\n5) Sides" +
                    "\n0) Done Adding Toppings!");

            int categoryChoice = console.promptForInt("Select topping category: ");

            String selectedCategory = null;
            Map<Integer, Topping> toppingsInSelectedCategory = null;

            switch (categoryChoice) {
                case 1: selectedCategory = Topping.MEAT; break;
                case 2: selectedCategory = Topping.CHEESE; break;
                case 3: selectedCategory = Topping.REGULAR; break;
                case 4: selectedCategory = Topping.SAUCE; break;
                case 5: selectedCategory = Topping.SIDE; break;
                case 0: return;
                default:
                    System.out.println("Invalid category. Please try again.");
                    continue;
            }

            toppingsInSelectedCategory = Topping.getToppingByCategory(selectedCategory);
            if (toppingsInSelectedCategory.isEmpty()){
                System.out.println("No toppings available in this category.");
                continue;
            }

            System.out.println("\n--- Available " + selectedCategory + " Toppings ---");
            toppingsInSelectedCategory.forEach((key, topping) ->
                    System.out.printf("%d) %s%n", key, topping.getName()));

            int toppingNumber = console.promptForInt("Enter topping number to add (0 to return to categories): ");
            if (toppingNumber == 0) {
                continue;
            }

            Topping selectedTopping = Topping.getByNumber(toppingNumber);
            if (selectedTopping == null || !selectedTopping.getCategory().equals(selectedCategory)) {
                System.out.println("Invalid topping number for the selected category " + selectedCategory + ". Please try again");
                continue;
            }

            boolean addAsExtra = false;
            if (selectedTopping.supportsExtra()) {
                String extraChoice = console.promptForString("\nAdd extra " + selectedTopping.getName() + "?" +
                        "(Yes/No):");
                if (extraChoice.equalsIgnoreCase("yes")) {
                    int currentExtraToppingCount = 0;
                    List<Topping> currentSandwichToppings = sandwich.getToppings();
                    List<Boolean> currentExtraToppings = sandwich.getToppingExtraStatuses();

                    for (int i = 0; i < currentSandwichToppings.size(); i++) {
                        if (currentSandwichToppings.get(i).getName().equals(selectedTopping.getName())
                        && currentExtraToppings.get(i)) {
                            currentExtraToppingCount++;
                        }
                    }

                    if (selectedTopping.getCategory().equals(Topping.MEAT)) {
                        if (currentExtraToppingCount < 2) {
                            addAsExtra = true;
                        }else {
                            System.out.println("Limit reached!" +
                                    "\nYou can add a maximum of 2 extra portions of " + selectedTopping.getName() + ".");
                        }
                    } else if (selectedTopping.getCategory().equals(Topping.CHEESE)) {
                        if (currentExtraToppingCount < 3) {
                            addAsExtra = true;
                        }else {
                            System.out.println("Limit reached!" +
                                    "\nYou can add a maximum of 3 extra portions of " + selectedTopping.getName() + ".");
                        }
                    }
                }
            }
            sandwich.addTopping(selectedTopping, addAsExtra);
            System.out.println(selectedTopping.getName() + (addAsExtra ? " (extra)" : "") + " added to your sandwich.");
            addMoreChoice = console.promptForString("\nAdd another topping? (Yes/No): ");
        }while (addMoreChoice.equalsIgnoreCase("yes"));
    }

    /**
     * Guides the user to remove a specific topping instance from the provided sandwich.
     * Displays a numbered list of current toppings for selection.
     *
     * @param sandwich The sandwich from which to remove toppings.
     */
    private void  removeToppingFromSandwich(Sandwich sandwich) {
        if (sandwich.getToppings().isEmpty()) {
            System.out.println("This sandwich has no toppings to remove.");
            return;
        }

        System.out.println("\n--- Remove Topping ---");
        displaySandwichDetails(sandwich);

        List<Topping> currentToppings = sandwich.getToppings();
        List<Boolean> toppingExtraStatus = sandwich.getToppingExtraStatuses();

        System.out.println("Select a topping to remove: ");
        for (int i = 0; i < currentToppings.size(); i++) {
            Topping t = currentToppings.get(i);
            boolean isExtra = toppingExtraStatus.get(i);
            System.out.printf("%d) %s %s%n", (i + 1), t.getName(), isExtra ? "(Extra)" : "");
        }
        System.out.println("0) Go back");

        int selection = console.promptForInt("\nEnter number of topping to remove (0 to go back): ");
        if (selection == 0) {
            return;
        }

        if (selection > 0 && selection <= currentToppings.size()) {
            int indexToRemove = selection - 1;

            Topping toppingToRemove = currentToppings.get(indexToRemove);
            boolean extraToppingToRemove = toppingExtraStatus.get(indexToRemove);

            sandwich.removeTopping(toppingToRemove, extraToppingToRemove);
            System.out.println(toppingToRemove.getName() + (extraToppingToRemove ? " (Extra)" : "") + " removed.");
        }else {
            System.out.println("Invalid selection. Please enter a number from the list.");
        }
    }

    /**
     * Prompts the user if they want the sandwich toasted and updates the sandwich's toasted status.
     *
     * @param sandwich The Sandwich object whose toasted status is to be set.
     */
    private void promptForToasting(Sandwich sandwich) {
        String currentStatus = sandwich.isToasted() ? "Yes" : "No";
        String toastedChoice = console.promptForString("Current toasted status: " + currentStatus + "." +
                "\nDo you want your sandwich toasted? (Yes/No): ");
        sandwich.setToasted(toastedChoice.equalsIgnoreCase("yes"));
        System.out.println("Sandwich will be " + (sandwich.isToasted() ? "toasted." : "not toasted."));
    }

    /**
     * Helper method to display the current comprehensive details of a sandwich.
     * This includes its name, bread type, size, toasted status, and a detailed list of all toppings
     * along with their "extra" status.
     *
     * @param sandwich The sandwich whose details are to be displayed.
     */
    private void displaySandwichDetails(Sandwich sandwich) {
        System.out.println("\n--- Current Sandwich Details ---" +
                "\nName: " + sandwich.getName() +
                "\nBread: " + sandwich.getBreadType() + " " + sandwich.getSize() + "\"" +
                "\nToasted: " + (sandwich.isToasted() ? "Yes" : "No") +
                "\nToppings:");
        if (sandwich.getToppings().isEmpty()) {
            System.out.println("  (No Toppings yet)");
        }else {
            List<Topping> toppings = sandwich.getToppings();
            List<Boolean> toppingExtraStatus = sandwich.getToppingExtraStatuses();
            for (int i = 0; i < toppings.size(); i++) {
                Topping t = toppings.get(i);
                boolean isExtra = toppingExtraStatus.get(i);
                System.out.printf("  - %s %s%n", t.getName(), isExtra ? "(Extra)" : "");
            }
        }
        System.out.printf("Current Price: $%.2f%n", sandwich.getPrice());
        System.out.println("----------------------------------");
    }
}
