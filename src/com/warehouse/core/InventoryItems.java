package com.warehouse.core;

class InventoryItem {
    String sku;
    String name;
    int stock;
    double price;

    public InventoryItem(String sku, String name, int stock, double price) {
        this.sku = sku;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("SKU: " + sku + " | Name: " + name + " | Stock: " + stock + " | Price: $" + price);
    }
}

public class InventoryItemExp1 {
    public static void main(String[] args) {
        System.out.println("===== CORE MODULE: CLASSES, OBJECTS & CONSTRUCTORS =====");
        InventoryItem item1 = new InventoryItem("CRATE-1001", "Barcode Scanner", 15, 89.99);
        InventoryItem item2 = new InventoryItem("CRATE-1002", "Label Printer", 8, 149.50);

        System.out.println("\n[Allocated Inventory Items]");
        item1.displayDetails();
        item2.displayDetails();
    }
}
