package com.momo.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.momo.model.Bill;
import com.momo.model.Customer;
import com.momo.service.PaymentManagement;

class PaymentManagementTest {

    @Test
    void testProcessPayment() {
        Customer customer = new Customer();
        customer.addFunds(600);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("Internet", 300, LocalDate.of(2024, 12, 20), "VNPT"));
        bills.add(new Bill("Electric", 300, LocalDate.of(2024, 12, 20), "Electronic company"));
        PaymentManagement paymentManagement = new PaymentManagement();
        
        paymentManagement.processPayment(customer, bills);
        assertEquals(0, customer.getBalance());
    }

    @Test
    void testProcessPaymentInsufficientFunds() {
        Customer customer = new Customer();
        customer.addFunds(100);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("Internet", 300, LocalDate.of(2024, 12, 20), "VNPT"));
        bills.add(new Bill("Electric", 300, LocalDate.of(2024, 12, 20), "Electronic company"));
        PaymentManagement paymentManagement = new PaymentManagement();

        paymentManagement.processPayment(customer, bills);
        assertEquals(100, customer.getBalance());
    }

    @Test
    void testSchedulePayment() {
        Customer customer = new Customer();
        Bill bill = new Bill("Electric", 400, LocalDate.of(2024, 12, 15), "EVN");
        PaymentManagement paymentManagement = new PaymentManagement();

        paymentManagement.schedulePayment(bill, LocalDate.of(2024, 12, 14));
        assertEquals(1, paymentManagement.listSchedulePayments().size());
    }

    @Test
    void testProcessScheduledPayments() {
        Customer customer = new Customer();
        customer.addFunds(500);
        Bill bill = new Bill("Electric", 400, LocalDate.of(2024, 12, 15), "EVN");
        PaymentManagement paymentManagement = new PaymentManagement();

        paymentManagement.schedulePayment(bill, LocalDate.now());
        paymentManagement.processScheduledPayments(customer);

        assertTrue(bill.isPaid());
        assertEquals(100, customer.getBalance());
    }

    @Test
    void testListPayments() {
        Customer customer = new Customer();
        customer.addFunds(1000);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill("Internet", 300, LocalDate.of(2024, 12, 20), "VNPT"));
        bills.add(new Bill("Electric", 300, LocalDate.of(2024, 12, 20), "Electronic company"));
        PaymentManagement paymentManagement = new PaymentManagement();

        paymentManagement.processPayment(customer, bills);

        assertEquals(2, paymentManagement.listPayments().size());
    }
}
