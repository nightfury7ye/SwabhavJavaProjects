package com.techlabs.unittesting.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEmployee {
	Employee employee;
	@BeforeEach
	void init() {
		employee = new Employee(1, "praveen", 30000);
	}
	@Test
	void testCalulateAllAllowance() {
		double total = 30000 + 6000 + 4500 + 3000;
		assertEquals(total, employee.calculateMonthlySalary(), "Montly Salary Match: ");
	}
	@Test
	void testCaclulateYearlySalary() {
		double total = (30000 + 6000 + 4500 + 3000) * 12;
		assertEquals(total, employee.caclulateYearlySalary(),"Yearly Salary Match: ");
	}

}
