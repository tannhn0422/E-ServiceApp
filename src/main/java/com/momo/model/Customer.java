package com.momo.model;

public class Customer {
	private static int counter = 1;
	private int id;
	private double balance;

    public Customer() {
    	this.id = counter++;
        this.balance = 0.0;
    }
    
    public Integer getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else {
            balance += amount;
        }
    }

    public boolean deductFunds(double amount) {
        if (amount > balance) {
        	System.out.println("Your balance is insufficient.");
            return false;
        }
        balance -= amount;
        return true;
    }
}
