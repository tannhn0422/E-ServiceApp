package com.momo.model;

import java.time.LocalDate;

public class SchedulePayment {
	 private final Bill bill;
    private final LocalDate scheduledDate;

    public SchedulePayment(Bill bill, LocalDate scheduledDate) {
        this.bill = bill;
        this.scheduledDate = scheduledDate;
    }

    public Bill getBill() {
        return bill;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }
}
