package com.warehouse.concurrency;

class SharedInventory {
    private int stock = 0;

    public synchronized void consumeStock(int quantity) {
        System.out.println("[Order Task]: Requesting " + quantity + " units of stock...");
        while (stock < quantity) {
            System.out.println("[Order Task]: Insufficient stock (" + stock + "). Calling wait()...");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
        stock -= quantity;
        System.out.println("[Order Task]: Fulfilled order of " + quantity + " units. Remaining stock: " + stock);
    }

    public synchronized void produceStock(int quantity) {
        System.out.println("[Supplier Task]: Restocking " + quantity + " units...");
        stock += quantity;
        System.out.println("[Supplier Task]: New stock level: " + stock + ". Calling notify()...");
        notify();
    }
}

class OrderTask implements Runnable {
    private SharedInventory inventory;
    private int quantity;

    public OrderTask(SharedInventory inventory, int quantity) {
        this.inventory = inventory;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        inventory.consumeStock(quantity);
    }
}

class SupplierTask implements Runnable {
    private SharedInventory inventory;
    private int quantity;

    public SupplierTask(SharedInventory inventory, int quantity) {
        this.inventory = inventory;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
        inventory.produceStock(quantity);
    }
}

public class InterthreadCommunication {
    public static void main(String[] args) {
        System.out.println("===== CONCURRENCY MODULE: INTER-THREAD COMMUNICATION =====");
        SharedInventory inventory = new SharedInventory();

        Thread consumerThread = new Thread(new OrderTask(inventory, 15), "ConsumerThread");
        Thread producerThread = new Thread(new SupplierTask(inventory, 20), "ProducerThread");

        consumerThread.start();
        producerThread.start();

        try {
            consumerThread.join();
            producerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\n[Inter-Thread Communication Completed Successfully]");
    }
}

