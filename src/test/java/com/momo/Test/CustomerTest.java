package com.momo.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.momo.model.Customer;

class CustomerTest {
		@Test
	    void testAddFunds() {
	        Customer customer = new Customer();
	        customer.addFunds(1000);
	        assertEquals(1000, customer.getBalance());
	    }

	    @Test
	    void testAddInvalidFunds() {
	        Customer customer = new Customer();
	        customer.addFunds(-500);
	        assertEquals(0, customer.getBalance()); // No funds should be added
	    }

	    @Test
	    void testDeductFunds() {
	        Customer customer = new Customer();
	        customer.addFunds(1000);
	        assertTrue(customer.deductFunds(500));
	        assertEquals(500, customer.getBalance());
	    }

	    @Test
	    void testDeductInsufficientFunds() {
	        Customer customer = new Customer();
	        customer.addFunds(500);
	        assertFalse(customer.deductFunds(1000));
	        assertEquals(500, customer.getBalance()); // Balance should remain unchanged
	    }
}

