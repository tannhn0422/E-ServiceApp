package com.momo.model;

import java.time.LocalDate;

public class Payment {
	private final Bill bill;
    private final LocalDate paymentDate;
    private final boolean isProcessed;

    public Payment(Bill bill, LocalDate paymentDate, boolean isProcessed) {
        this.bill = bill;
        this.paymentDate = paymentDate;
        this.isProcessed = isProcessed;
    }

    @Override
    public String toString() {
        return String.format("Payment for Bill ID: %d on %s, status: %s",
                bill.getId(), paymentDate, isProcessed ? "PROCESSED" : "PENDING");
    }
}
