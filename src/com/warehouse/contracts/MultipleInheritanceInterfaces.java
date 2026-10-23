package com.warehouse.contracts;

interface Shippable {
    double calculateShippingCost();
}

interface Taxable {
    double calculateTax();
}

abstract class InventoryItemBase {
    private static int idCounter = 1000;
    private String sku;
    private String name;
    private int stock;
    private double price;

    public InventoryItemBase(String name, int stock, double price) {
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

class PerishableItemTaxed extends InventoryItemBase implements Taxable {
    private String expiryDate;

    public PerishableItemTaxed(String name, int stock, double price, String expiryDate) {
        super(name, stock, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.05;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() + calculateTax();
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Tax: $" + calculateTax() + " | Expiry: " + expiryDate + " | Final Price: $" + calculateFinalPrice());
    }
}

class ElectronicsItemShippable extends InventoryItemBase implements Shippable, Taxable {
    private int warrantyMonths;

    public ElectronicsItemShippable(String name, int stock, double price, int warrantyMonths) {
        super(name, stock, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double calculateShippingCost() {
        return 15.00;
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.18;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() + calculateTax() + calculateShippingCost();
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" | Tax: $" + calculateTax() + " | Ship: $" + calculateShippingCost() +
                " | Warranty: " + warrantyMonths + " Mos | Final Price: $" + calculateFinalPrice());
    }
}

public class MultipleInheritanceInterfaces {
    public static void main(String[] args) {
        System.out.println("===== CONTRACTS MODULE: MULTIPLE INHERITANCE VIA INTERFACES =====");
        PerishableItemTaxed item1 = new PerishableItemTaxed("Lithium Ion Batteries", 200, 15.00, "12/2028");
        ElectronicsItemShippable item2 = new ElectronicsItemShippable("Smart Sensor Hub", 10, 100.00, 24);

        System.out.println("\n[Multiple Inheritance Output]");
        item1.displayDetails();
        item2.displayDetails();
    }
}
