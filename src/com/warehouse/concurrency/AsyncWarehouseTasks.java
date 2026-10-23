package com.warehouse.concurrency;

class StockAuditThread extends Thread {
    private String sku;

    public StockAuditThread(String threadName, String sku) {
        super(threadName);
        this.sku = sku;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " [Audit]: Verifying " + sku + " - Step " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }
    }
}

class StockSyncTask implements Runnable {
    private String sku;

    public StockSyncTask(String sku) {
        this.sku = sku;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " [Sync]: Cloud Backup for " + sku + " - Step " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }
    }
}

public class AsyncWarehouseTasks {
    public static void main(String[] args) {
        System.out.println("===== CONCURRENCY MODULE: MULTITHREADING IN JAVA =====");

        System.out.println("\n--- Part A: Threads using Extends Thread ---");
        StockAuditThread auditThread1 = new StockAuditThread("Audit-Thread-1", "CRATE-1001");
        StockAuditThread auditThread2 = new StockAuditThread("Audit-Thread-2", "CRATE-1002");

        auditThread1.start();
        auditThread2.start();

        try {
            auditThread1.join();
            auditThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\n--- Part B: Threads using Implements Runnable ---");
        StockSyncTask syncTask1 = new StockSyncTask("CRATE-1001");
        StockSyncTask syncTask2 = new StockSyncTask("CRATE-1002");

        Thread syncThread1 = new Thread(syncTask1, "Sync-Thread-1");
        Thread syncThread2 = new Thread(syncTask2, "Sync-Thread-2");

        syncThread1.start();
        syncThread2.start();

        try {
            syncThread1.join();
            syncThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\n[All Warehouse Threads Finished Execution]");
    }
}
