package com.warehouse.exceptions;

class InvalidStockException extends Exception {
    public InvalidStockException(String message) {
        super(message);
    }
}

class InventoryItem {
    private static int idCounter = 1000;
    private String sku;
    private String name;
    private int stock;
    private double price;

    public InventoryItem(String name, int stock, double price) throws InvalidStockException {
        if (stock < 0) {
            throw new InvalidStockException("Initial stock cannot be negative: " + stock);
        }
        if (price < 0.0) {
            throw new InvalidStockException("Price cannot be negative: $" + price);
        }
        this.sku = "CRATE-" + (++idCounter);
        this.name = name;
        this.stock = stock;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void reduceStock(int quantity) throws InvalidStockException {
        if (quantity <= 0) {
            throw new InvalidStockException("Quantity to reduce must be positive: " + quantity);
        }
        if (quantity > stock) {
            throw new InvalidStockException("Insufficient stock! Available: " + stock + ", Requested: " + quantity);
        }
        this.stock -= quantity;
        System.out.println("Successfully reduced stock by " + quantity + ". Remaining stock: " + stock);
    }

    public void displayDetails() {
        System.out.println("SKU: " + sku + " | Name: " + name + " | Stock: " + stock + " | Price: $" + price);
    }
}

public class StockValidationSystem {
    public static void main(String[] args) {
        System.out.println("===== EXCEPTIONS MODULE: EXCEPTION HANDLING =====");
        try {
            System.out.println("\n[Creating Valid Item]");
            InventoryItem item1 = new InventoryItem("Barcode Scanner", 15, 89.99);
            item1.displayDetails();

            System.out.println("\n[Valid Stock Reduction]");
            item1.reduceStock(5);

            System.out.println("\n[Triggering Insufficient Stock Exception]");
            item1.reduceStock(20);
        } catch (InvalidStockException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("[Finally Block Executed: Transaction Logged]");
        }

        try {
            System.out.println("\n[Triggering Invalid Constructor Input Exception]");
            InventoryItem item2 = new InventoryItem("Faulty Printer", -10, 50.00);
        } catch (InvalidStockException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("[Finally Block Executed: Operation Terminated]");
        }
    }
}
