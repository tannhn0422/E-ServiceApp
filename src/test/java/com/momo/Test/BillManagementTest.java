package com.momo.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.momo.model.Bill;
import com.momo.service.BillManagement;

class BillManagementTest {

    @Test
    void testAddBill() {
        BillManagement billManagement = new BillManagement();
        Bill bill = new Bill("Electric", 200, LocalDate.of(2024, 12, 15), "EVN");
        billManagement.saveBill(bill);
        assertEquals(1, billManagement.listBills().size());
        assertEquals(bill, billManagement.listBills().get(0));
    }

    @Test
    void testFindBillById() {
        BillManagement billManagement = new BillManagement();
        Bill bill = new Bill("Water", 100, LocalDate.of(2024, 12, 10), "SAVACO");
        billManagement.saveBill(bill);
        assertTrue(billManagement.findBillById(bill.getId()).isPresent());
    }

    @Test
    void testFindBillByInvalidId() {
        BillManagement billManagement = new BillManagement();
        assertTrue(!billManagement.findBillById(999).isPresent());
    }

    @Test
    void testGetUnpaidBillsByDueDate() {
        BillManagement billManagement = new BillManagement();
        Bill bill1 = new Bill("Electric", 200, LocalDate.of(2024, 12, 15), "EVN");
        Bill bill2 = new Bill("Water", 100, LocalDate.of(2024, 12, 10), "SAVACO");
        billManagement.saveBill(bill1);
        billManagement.saveBill(bill2);

        List<Bill> unpaidBills = billManagement.getUnpaidBillsByDueDate();
        assertEquals(2, unpaidBills.size());
        assertEquals(bill2, unpaidBills.get(0)); // Due date sorting
    }
}
