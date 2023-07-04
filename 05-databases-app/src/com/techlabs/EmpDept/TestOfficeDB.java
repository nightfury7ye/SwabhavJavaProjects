package com.techlabs.EmpDept;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestOfficeDB {

	public static void main(String[] args) {
		OfficeDB officeDB = OfficeDB.getDBconnection();     
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean end = false;
        do {
            displayMenu();
            System.out.println();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    officeDB.insertDEPT(reader);
                    break;
                case 2:
                	officeDB.insertEMP(reader);
                    break;
                case 3:
                	officeDB.displayAllEmpWithDeptName(scanner);
                    break;
                case 4:
                	officeDB.incrementComm();
                    break;
                case 5:
                	officeDB.deleteSalaryLessThan(scanner);
                    break;
                case 6:
                	officeDB.displayEMP();
                    break;
                case 7:
                	officeDB.displayDEPT();
                    break;
                case 8:
                	end = true;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (end == false);
        
	}
	
    private static void displayMenu() {
        System.out.println("---------- MENU ----------");
        System.out.println("1. Insert Records into Department");
        System.out.println("2. Insert Records into Employee");
        System.out.println("3. Display Employees in a particular Department");
        System.out.println("4. Update Employee Records (Increase Commission by 20%)");
        System.out.println("5. Delete Employees by Salary");
        System.out.println("6. Display All Employees");
        System.out.println("7. Display All Departments");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

}
