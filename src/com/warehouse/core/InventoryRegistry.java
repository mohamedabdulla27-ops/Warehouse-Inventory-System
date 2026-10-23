package com.warehouse.core;

class InventoryItem {
    private static int idCounter = 1000;
    private static int totalItemsCreated = 0;
    private String sku;
    private String name;
    private int stock;
    private double price;

    public InventoryItem(String name, int stock, double price) {
        this.sku = "CRATE-" + (++idCounter);
        this.name = name;
        setStock(stock);
        setPrice(price);
        totalItemsCreated++;
    }

    public static int getTotalItemsCreated() {
        return totalItemsCreated;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

public class InventoryRegistry {
    public static void main(String[] args) {
        System.out.println("===== CORE MODULE: STATIC MEMBERS & ACCESS MODIFIERS =====");
        InventoryItem item1 = new InventoryItem("Barcode Scanner", 15, 89.99);
        InventoryItem item2 = new InventoryItem("Label Printer", 8, 149.50);

        System.out.println("\n[Allocated Inventory Items]");
        item1.displayDetails();
        item2.displayDetails();

        System.out.println("\nTotal Items Created: " + InventoryItem.getTotalItemsCreated());
    }
}
