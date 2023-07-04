package com.techlabs.unittesting.mockito;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class EmployeeTest {
	@Mock
	Department department;
	
	Employee employee;
	
	@BeforeEach
	void init() {
		Department department = mock(Department.class);
		employee = new Employee(1, "Praveen", department);
	}
	@Test
	void testDepartment() {

	}

}
