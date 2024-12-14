package com.momo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.momo.model.Bill;
import com.momo.model.Customer;
import com.momo.model.Payment;
import com.momo.model.SchedulePayment;

public class PaymentManagement {
	private final List<Payment> payments = new ArrayList<>();
	private final List<SchedulePayment> schedulePayments = new ArrayList<>();
	
    public void processPayment(Customer customer, List<Bill> bills) {
     // Filter unpaid bills
        List<Bill> unpaidBills = bills.stream()
                .filter(bill -> !bill.isPaid())
                .sorted(Comparator.comparing(Bill::getDueDate)) // Sort by due date
                .collect(Collectors.toList());

        for (Bill bill : unpaidBills) {
            if (customer.deductFunds(bill.getAmount())) {
                bill.markAsPaid();
                payments.add(new Payment(bill, LocalDate.now(), true));
                System.out.println("Payment successful for Bill ID: " + bill.getId());
            } else {
                System.out.println("Insufficient funds for Bill ID: " + bill.getId());
            }
        }
    }
    
    public void schedulePayment(Bill bill, LocalDate date) {
        if (bill.isPaid()) {
            System.out.println("Bill is already paid. Cannot schedule payment.");
        } else {
        	schedulePayments.add(new SchedulePayment(bill, date));
            System.out.println("Payment for Bill ID: " + bill.getId() + " scheduled on " + date);
        }
    }
    
    public void processScheduledPayments(Customer customer) {
        LocalDate today = LocalDate.now();
        List<SchedulePayment> processed = new ArrayList<>();

        for (SchedulePayment sp : schedulePayments) {
            if (!sp.getBill().isPaid() && !today.isBefore(sp.getScheduledDate())) {
                if (customer.deductFunds(sp.getBill().getAmount())) {
                    sp.getBill().markAsPaid();
                    payments.add(new Payment(sp.getBill(), today, true));
                    System.out.println("Scheduled payment successful for Bill ID: " + sp.getBill().getId());
                    processed.add(sp);
                } else {
                    System.out.println("Insufficient funds for scheduled payment of Bill ID: " + sp.getBill().getId());
                }
            }
        }

        // Remove processed scheduled payments
        schedulePayments.removeAll(processed);
    }
	
    public List<Payment> listPayments() {
        return payments;
    }
    
    public List<SchedulePayment> listSchedulePayments() {
        return new ArrayList<>(schedulePayments);
    }
    
}
