package com.warehouse.input;

import java.util.Scanner;

class InventoryItem {
    private static int idCounter = 1000;
    private String sku;
    private String name;
    private int stock;
    double price;

    public InventoryItem(String name, int stock, double price) {
        this.sku = "CRATE-" + (++idCounter);
        this.name = name;
        setStock(stock);
        setPrice(price);
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        } else {
            this.price = 0.0;
        }
    }

    public void displayDetails() {
        System.out.println("SKU: " + sku + " | Name: " + name + " | Stock: " + stock + " | Price: $" + price);
    }
}

public class ConsoleInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== INPUT MODULE: SCANNER INTERFACE =====");
        while (running) {
            System.out.println("\n1. Add Inventory Item");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Item Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Stock Quantity: ");
                int stock = scanner.nextInt();
                System.out.print("Enter Unit Price ($): ");
                double price = scanner.nextDouble();

                InventoryItem item = new InventoryItem(name, stock, price);
                System.out.println("\n[Registered Item]");
                item.displayDetails();
            } else if (choice == 2) {
                System.out.println("Exiting System...");
                running = false;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}
