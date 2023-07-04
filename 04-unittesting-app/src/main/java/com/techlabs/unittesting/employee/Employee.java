package com.techlabs.unittesting.employee;

public class Employee {
	int id;
	String name;
	double basic, hra, da, ta;
	public Employee(int id, String name, double basic) {
		super();
		this.id = id;
		this.name = name;
		this.basic = basic;
		calculateAllAllowance();
	}
	private void calculateAllAllowance() {
		hra = (basic* 20) / 100;
		da = (15 *basic) / 100;
		ta = (10 * basic) / 100;
	}
	
	public double calculateMonthlySalary() {
		return basic + hra + da + ta;
	}
	public double caclulateYearlySalary() {
		return calculateMonthlySalary() * 12;
	}
	
}
