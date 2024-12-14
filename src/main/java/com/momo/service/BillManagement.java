package com.momo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.momo.model.Bill;

public class BillManagement {
	private final List<Bill> bills = new ArrayList<>();

    public Bill saveBill(Bill bill) {
    	bills.add(bill);
    	return bill;
    }
   

    public List<Bill> listBills() {
        return bills;
    }

    public Optional<Bill> findBillById(int id) {
        return bills.stream().filter(b -> b.getId() == id).findFirst();
    }
    
    public void showlistBills() {
    	listBills().forEach(System.out::println);
    }
    
    public List<Bill> getUnpaidBillsByDueDate() {
        return bills.stream()
                .filter(bill -> !bill.isPaid()) // Filter unpaid bills
                .sorted(Comparator.comparing(Bill::getDueDate)) // Sort by due date
                .collect(Collectors.toList());
    }
    
    public List<Bill> searchBillsByProvider(String provider) {
        return bills.stream()
                .filter(bill -> bill.getProvider().equalsIgnoreCase(provider))
                .collect(Collectors.toList());
    }
}
