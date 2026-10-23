package com.warehouse.contracts;

abstract class InventoryItem {
    private static int idCounter = 1000;
    private String sku;
    private String name;
    private int stock;
    private double price;

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

    public abstract double calculateFinalPrice();

    public void displayDetails() {
        System.out.print("SKU: " + sku + " | Name: " + name + " | Stock: " + stock + " | Base Price: $" + price);
    }
}

class PerishableItem extends InventoryItem {
    private String expiryDate;

    public PerishableItem(String name, int stock, double price, String expiryDate) {
        super(name, stock, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * 1.05;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Expiry: " + expiryDate + " | Final Price (5% Tax): $" + calculateFinalPrice());
    }
}

class ElectronicsItem extends InventoryItem {
    private int warrantyMonths;

    public ElectronicsItem(String name, int stock, double price, int warrantyMonths) {
        super(name, stock, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() * 1.18;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Warranty: " + warrantyMonths + " Mos | Final Price (18% Tax): $" + calculateFinalPrice());
    }
}

public class AbstractCatalog {
    public static void main(String[] args) {
        System.out.println("===== CONTRACTS MODULE: ABSTRACT CLASSES & METHODS =====");
        InventoryItem item1 = new PerishableItem("Lithium Ion Batteries", 200, 15.00, "12/2028");
        InventoryItem item2 = new ElectronicsItem("Smart Sensor Hub", 10, 100.00, 24);

        System.out.println("\n[Abstract Reference Execution]");
        item1.displayDetails();
        item2.displayDetails();
    }
}
