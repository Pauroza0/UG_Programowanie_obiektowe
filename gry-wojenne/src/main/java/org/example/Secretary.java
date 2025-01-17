package org.example;

import java.util.ArrayList;
import java.util.List;

public class Secretary {
    private static Secretary instance;
    private final List<String> logs;

    private Secretary() {
        this.logs = new ArrayList<>();
    }

    public static Secretary getInstance() {
        if (instance == null) {
            synchronized (Secretary.class) {
                if (instance == null) {
                    instance = new Secretary();
                }
            }
        }
        return instance;
    }

    public void logAction(String name, String actionType, String details) {
        String logEntry = name + " - " + actionType + ": " + details;
        logs.add(logEntry);
        System.out.println(logEntry);
    }

    public void generateReport(General general1, General general2) {
        System.out.println("=== Report by Secretary ===");
        System.out.println("General 1:");
        general1.printReport();
        System.out.println();

        System.out.println("General 2:");
        general2.printReport();
        System.out.println("===========================");

        System.out.println("=== Action Logs ===");
        logs.forEach(System.out::println);
    }
}