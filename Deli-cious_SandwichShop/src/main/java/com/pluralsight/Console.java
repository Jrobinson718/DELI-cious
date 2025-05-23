package com.pluralsight;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);

    public int promptForInt(String prompt) {
        boolean hasResult = false;
        int result = 0;
        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("Invalid entry");
                scanner.next();
            }
        }
        return result;
    }

    public long promptForLong(String prompt) {
        boolean hasResult = false;
        long result = 0;
        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextLong();
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("Invalid entry");
                scanner.next();
            }
        }
        return result;
    }

    public float promptForFloat(String prompt) {
        boolean hasResult = false;
        float result = 0;
        while (!hasResult) {
            try {
                System.out.print(prompt);
                result = scanner.nextFloat();
                scanner.nextLine();
                hasResult = true;

            } catch (Exception e) {
                System.out.println("Invalid entry");
                scanner.next();
            }
        }
        return result;
    }

    public String promptForString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            }
        }
    }
}