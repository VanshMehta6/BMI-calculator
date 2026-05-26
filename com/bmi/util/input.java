package com.bmi.util;

import java.util.Scanner;

public class input {
    private final Scanner scanner;
    
    public input(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("❌ Enter between %d and %d\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
            }
        }
    }
    
    public double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("❌ Enter between %.1f and %.1f\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
            }
        }
    }
    
    public String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("❌ Input cannot be empty.");
        }
    }
    
    public int getMenuChoice(String prompt, int max) {
        return getIntInput(prompt, 1, max);
    }
}