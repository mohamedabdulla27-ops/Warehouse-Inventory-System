package com.warehouse.core;

class PackagedItem {
    private static int idCounter = 1000;
    private String sku;
    private String name;
    private int stock;
    private double price;

    public PackagedItem(String name, int stock, double price) {
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

public class PackagedInventory {
    public static void main(String[] args) {
        System.out.println("===== CORE MODULE: USER-DEFINED PACKAGES =====");
        PackagedItem item1 = new PackagedItem("Barcode Scanner", 15, 89.99);
        PackagedItem item2 = new PackagedItem("Label Printer", 8, 149.50);

        System.out.println("\n[Packaged Inventory Execution]");
        item1.displayDetails();
        item2.displayDetails();
    }
}
