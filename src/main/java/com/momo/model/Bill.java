package com.momo.model;

import java.time.LocalDate;

public class Bill {
	private static int counter = 1;
    private final int id;
    private String type;
    private double amount;
    private LocalDate dueDate;
    private String provider;
    private boolean isPaid;

    public Bill(String type, double amount, LocalDate dueDate, String provider) {
        this.id = counter++;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.provider = provider;
        this.isPaid = false;
    }
    
    public void setBill(String type, double amount, LocalDate dueDate, String provider) {
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.provider = provider;
        this.isPaid = false;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getProvider() {
        return provider;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return String.format("Bill ID: %d, Type: %s, Amount: %.2f, Due Date: %s, State: %s, Provider: %s",
                id, type, amount, dueDate, isPaid ? "PAID" : "NOT_PAID", provider);
    }
}
