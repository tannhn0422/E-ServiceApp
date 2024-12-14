package com.momo.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.momo.constant.CommonConstants;
import com.momo.model.Bill;
import com.momo.model.Customer;
import com.momo.service.BillManagement;
import com.momo.service.PaymentManagement;

public class Application {
	static Scanner scanner = new Scanner(System.in);
	 public static void main(String[] args) {
	        @SuppressWarnings("resource")
			
	        Customer customer = new Customer();
	        BillManagement billManagement = new BillManagement();
	        //data for testing
	        billManagement.saveBill(new Bill("ELECTRIC", 20000, LocalDate.now(), "VNPT"));
	        billManagement.saveBill(new Bill("WI-FI", 50000, LocalDate.now(), "FPT"));
	        billManagement.saveBill(new Bill("WATER", 10000, LocalDate.now(), "FPT"));
	        
	        PaymentManagement paymentManagement = new PaymentManagement();

	        while (true) {
	            System.out.println("\nEnter Command:");
	            String command = scanner.nextLine().trim();
	            String[] parts = command.split(" ");
	            String action = parts[0].toUpperCase();

	            switch (action) {
	                case CommonConstants.CASH_IN:
	                    double amount = Double.parseDouble(parts[1]);
	                    customer.addFunds(amount);
	                    System.out.println("Current Balance: " + customer.getBalance());
	                    break;
	                case CommonConstants.SEARCH_BILL:
	                	System.out.println("Please type the id of bill you need to search:");
	                    int idSearch = Integer.parseInt(scanner.next().trim());
	                    Optional<Bill> billSearched = billManagement.findBillById(idSearch);
	                    System.out.println("Your bill: " + billSearched.get());
	                    break;
	                case CommonConstants.ADD_BILL:
	                    billManagement.saveBill(inputBill(null)); //0 for new bill
	                    System.out.println("Bill added successfully.");
	                    break;
	                case CommonConstants.UPDATE_BILL:
	                	billManagement.showlistBills();
	                	System.out.println("Please choose the bill you need to update:");
	                    int id = Integer.parseInt(scanner.next().trim());
	                    Optional<Bill> bill = billManagement.findBillById(id);
	                    if (bill.isPresent()) {
	                    	inputBill(bill.get());
	                    } else {
	                    	System.out.println("The bill you select does not exist, please retry again");
	                    }
	                	break;
	                case CommonConstants.LIST_BILL:
	                	billManagement.showlistBills();
	                    break;
	                case CommonConstants.SEARCH_BILL_BY_PROVIDER:
	                    String providerName = parts[1];
	                    List<Bill> matchedBills = billManagement.searchBillsByProvider(providerName);
	                    if (matchedBills.isEmpty()) {
	                        System.out.println("No bills found for the provider: " + providerName);
	                    } else {
	                        System.out.println("Bills from provider " + providerName + ":");
	                        for (Bill item : matchedBills) {
	                            System.out.println(item);
	                        } 
	                    }
	                    break;
	                case CommonConstants.PAY:
	                	List<Bill> bills = new ArrayList<>();
	                	for(int i = 1; i <= parts.length - 1; i++) {
	                		int billId = Integer.parseInt(parts[i]);
	                		 Optional<Bill> billPayment = billManagement.findBillById(billId);
	                		 if(billPayment.isPresent()) {
	                			 bills.add(billPayment.get());
	                		 } else {
	                			 System.out.println("The bill id "+ billId +" does not exist, please check again");
	                		 }
	                	}
	                	paymentManagement.processPayment(customer, bills);
	                    
	                    break;
	                case CommonConstants.LIST_PAYMENT:
	                	paymentManagement.listPayments().forEach(System.out::println);
	                    break;
	                case CommonConstants.LIST_DUE_DATE:
	                	List<Bill> unpaidBills = billManagement.getUnpaidBillsByDueDate();
	                    if (unpaidBills.isEmpty()) {
	                        System.out.println("No unpaid bills.");
	                    } else {
	                        System.out.println("Upcoming unpaid bills:");
	                        for (Bill unpaidBill : unpaidBills) {
	                            System.out.println(unpaidBill);
	                        }
	                    }
	                    break;
	                case CommonConstants.SCHEDULE:
	                    if (parts.length < 3) {
	                        System.out.println("Usage: SCHEDULE <Bill ID> <YYYY-MM-DD>");
	                    } else {
	                        int billId = Integer.parseInt(parts[1]);
	                        LocalDate scheduledDate = LocalDate.parse(parts[2]);
	                        Optional<Bill> billSchedule = billManagement.findBillById(billId);
	                        if (billSchedule.isPresent()) {
	                        	paymentManagement.schedulePayment(billSchedule.get(), scheduledDate);
	                        } else {
	                        	System.out.println("Bill "+ billId +" not found.");
	                        }
	                    }
	                    break;
	                case CommonConstants.EXIT:
	                    System.out.println("Thanks, Goodbye!");
	                    return;
	                default:
	                    System.out.println("Unknown command.");
	            }
	        }
	        
	    }
	 
	 public static Bill inputBill(Bill bill) {
		 System.out.println("Please input bill type: ");
         String type = scanner.next().trim();
         System.out.println("Please input bill amount: ");
         double billAmount = Double.parseDouble(scanner.next().trim());
         System.out.println("Please input bill due date(yyyy-MM-dd): ");
         LocalDate dueDate = LocalDate.parse(scanner.next().trim());
         System.out.println("Please input bill provider: ");
         String provider = scanner.next().trim();
         
         if(bill != null) {
        	 bill.setBill(type, billAmount, dueDate, provider);
        	 return bill;
         }
         return new Bill(type, billAmount, dueDate, provider);	
		
	 }
	 
}
