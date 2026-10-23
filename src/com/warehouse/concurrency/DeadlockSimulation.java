package com.warehouse.concurrency;

class WarehouseSection {
    private String name;

    public WarehouseSection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class DeadlockSimulation {
    public static void main(String[] args) {
        System.out.println("===== CONCURRENCY MODULE: DEADLOCK IN MULTITHREADING =====");

        final WarehouseSection sectionA = new WarehouseSection("Zone A (Storage)");
        final WarehouseSection sectionB = new WarehouseSection("Zone B (Dispatch)");

        // Thread 1: Locks Section A first, then tries to lock Section B
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sectionA) {
                    System.out.println("[Thread 1]: Locked " + sectionA.getName());
                    try {
                        Thread.sleep(100); // Gives Thread 2 time to lock Section B
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted.");
                    }
                    System.out.println("[Thread 1]: Waiting to lock " + sectionB.getName() + "...");
                    synchronized (sectionB) {
                        System.out.println("[Thread 1]: Successfully locked " + sectionB.getName());
                    }
                }
            }
        }, "Worker-1");

        // Thread 2: Locks Section B first, then tries to lock Section A
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sectionB) {
                    System.out.println("[Thread 2]: Locked " + sectionB.getName());
                    try {
                        Thread.sleep(100); // Gives Thread 1 time to lock Section A
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted.");
                    }
                    System.out.println("[Thread 2]: Waiting to lock " + sectionA.getName() + "...");
                    synchronized (sectionA) {
                        System.out.println("[Thread 2]: Successfully locked " + sectionA.getName());
                    }
                }
            }
        }, "Worker-2");

        thread1.start();
        thread2.start();
    }
}
