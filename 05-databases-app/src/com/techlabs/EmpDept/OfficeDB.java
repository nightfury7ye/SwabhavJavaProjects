package com.techlabs.EmpDept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class OfficeDB {
	private static OfficeDB officeDB;
	private static String localhost_url = "jdbc:mysql://localhost/";
	private static String user = "root";
	private static String pass = "Praveen7ye";
	private Connection connection = null;
	private Statement statement = null;
	private OfficeDB() {
		connect();
	}
	public static OfficeDB getDBconnection() {
		if(officeDB == null)
			officeDB = new OfficeDB();
		return officeDB;
	}
	
	void connect() {
		try {
			if(connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(localhost_url, user, pass);
				createStatement();
				createDatabase();
				System.out.println("Connecton Established");
				createDepartmentTable();
				createEmployeeTable();
			}
			else {
			System.out.println("Connecton Already Established");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void createStatement() throws SQLException {
		statement = connection.createStatement();
	}
	void createDatabase() throws SQLException {
		try {
	        statement.execute("CREATE DATABASE OFFICE");
	        System.out.println("Database created successfully...");  
		} catch (SQLException e) {
			statement.execute("USE OFFICE");
		}
	};
	void createDepartmentTable() {
		String DeptQuery = "CREATE TABLE DEPT ("
				+ "DEPTNO integer NOT NULL,"
				+ "DNAME varchar(14),"
				+ "LOC varchar(13),"
				+ "CONSTRAINT DEPT_PRIMARY_KEY PRIMARY KEY (DEPTNO))";
		try {
			statement.execute(DeptQuery);
		} catch (SQLException e) {
			System.out.println("DEPT Table Already exists");
		}
	}
	void createEmployeeTable() {
		String EmpQuery = " CREATE TABLE EMP ("
				+ " EMPNO integer NOT NULL,"
				+ " ENAME varchar(10),"
				+ " JOB varchar(9),"
				+ " MGR integer,"
				+ " HIREDATE DATEtime,"
				+ " SAL DECIMAL(10,2),"
				+ " COMM DECIMAL(10,2),"
				+ " DEPTNO integer NOT NULL,"
				+ " CONSTRAINT EMP_FOREIGN_KEY FOREIGN KEY (DEPTNO) REFERENCES DEPT (DEPTNO),"
				+ " CONSTRAINT EMP_PRIMARY_KEY PRIMARY KEY (EMPNO));";
		try {
			statement.execute(EmpQuery);
		} catch (SQLException e) {
			System.out.println("EMP Table Already exists");
		}
	}
	
	void insertALLDEPT(List<Department> deptList) {
		for(Department dept: deptList) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("insert into DEPT (DEPTNO, DNAME, LOC) value(?,?,?)");
				preparedStatement.setInt(1, dept.getDeptNO());
				preparedStatement.setString(2, dept.getdNAME());
				preparedStatement.setString(3, dept.getLoc());
				preparedStatement.executeUpdate();
				System.out.println("Row inserted");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	void insertALLEMP(List<Employee> EMPList) {
		for(Employee emp: EMPList) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("insert into EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) "
						+ "value(?,?,?,?,?,?,?,?)");
				preparedStatement.setInt(1, emp.getEmpNO());
				preparedStatement.setString(2, emp.geteNAME());
				preparedStatement.setString(3, emp.getJob());
				preparedStatement.setObject(4, emp.getManager());
				preparedStatement.setDate(5, emp.getHireDate());
				preparedStatement.setDouble(6, emp.getSal());
				preparedStatement.setObject(7, emp.getComm());
				preparedStatement.setInt(8, emp.getDeptNO());
				
				preparedStatement.executeUpdate();
				System.out.println("Row inserted");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	void displayAllEmpWithDeptName(Scanner scanner) {
		System.out.println("Enter Dept Name");
		String deptName = scanner.next();
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT E.*,D.DNAME AS DNAME FROM EMP E JOIN DEPT D ON E.DEPTNO = D.DEPTNO "
					+ "WHERE D.DNAME = ?");
			preparedStatement.setString(1, deptName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("EMPNO") + "\t" + resultSet.getString("ENAME")+ "\t" + resultSet.getString("DNAME"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	void incrementComm() {
		try {
			statement.executeUpdate("UPDATE EMP SET COMM = COMM * 1.2");
			System.out.println("Record Updated");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	void deleteSalaryLessThan(Scanner scanner) {
		System.out.println("Specify Minimum salary");
		double sal = scanner.nextDouble();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM EMP WHERE SAL < ?");
			preparedStatement.setDouble(1, sal);
			preparedStatement.executeUpdate();
			System.out.println("Record Deleted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		scanner.close();
	}
	void displayEMP() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM EMP");
			System.out.println("\nEMP TABLE\n");
			while(resultSet.next()) {
			    int empNo = resultSet.getInt("EMPNO");
			    String eName = resultSet.getString("ENAME");
			    String job = resultSet.getString("JOB");
			    int mgr = resultSet.getInt("MGR");
			    Date hireDate = resultSet.getDate("HIREDATE");
			    double sal = resultSet.getDouble("SAL");
			    double comm = resultSet.getDouble("COMM");
			    int deptNo = resultSet.getInt("DEPTNO");

			    System.out.println(empNo + "\t" + eName + "\t" + job + "\t" + mgr + "\t" + hireDate + "\t" + sal + "\t" + comm + "\t" + deptNo);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void displayDEPT() {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPT");
			System.out.println("\nDEPT TABLE\n");
			while(resultSet.next()) {
			    System.out.println(resultSet.getInt("DEPTNO") + "\t" + resultSet.getString("DNAME") + "\t" + resultSet.getString("LOC"));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void insertDEPT(BufferedReader reader) {
		 Department dept = null;

		try {
	        System.out.print("Enter DEPTNO: ");
	        int deptNo;
			deptNo = Integer.parseInt(reader.readLine());
	        System.out.print("Enter DNAME: ");
	        String dname = reader.readLine();
	        System.out.print("Enter LOC: ");
	        String loc = reader.readLine();

	        dept = new Department(deptNo, dname, loc);
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("insert into DEPT (DEPTNO, DNAME, LOC) value(?,?,?)");
				preparedStatement.setInt(1, dept.getDeptNO());
				preparedStatement.setString(2, dept.getdNAME());
				preparedStatement.setString(3, dept.getLoc());
				preparedStatement.executeUpdate();
				System.out.println("Row inserted");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	void insertEMP(BufferedReader reader) {
		try {
            System.out.print("Enter empNo: ");
            int empNo = Integer.parseInt(reader.readLine());

            System.out.print("Enter manager: ");
            Integer manager = Integer.parseInt(reader.readLine());

            Integer deptNo = null;

            System.out.print("Enter eNAME: ");
            String eNAME = reader.readLine();

            System.out.print("Enter job: ");
            String job = reader.readLine();

            System.out.print("Enter hireDate (yyyy-MM-dd): ");
            String hireDateStr = reader.readLine();
            Date hireDate = Date.valueOf(hireDateStr);

            System.out.print("Enter sal: ");
            double sal = Double.parseDouble(reader.readLine());

            System.out.print("Enter comm: ");
            Double comm = Double.parseDouble(reader.readLine());

            Employee emp = new Employee(empNo, manager, deptNo, eNAME, job, hireDate, sal, comm);
		 
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("insert into EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) "
						+ "value(?,?,?,?,?,?,?,?)");
				preparedStatement.setInt(1, emp.getEmpNO());
				preparedStatement.setString(2, emp.geteNAME());
				preparedStatement.setString(3, emp.getJob());
				preparedStatement.setObject(4, emp.getManager());
				preparedStatement.setDate(5, emp.getHireDate());
				preparedStatement.setDouble(6, emp.getSal());
				preparedStatement.setObject(7, emp.getComm());
				preparedStatement.setInt(8, emp.getDeptNO());
				
				preparedStatement.executeUpdate();
				System.out.println("Row inserted");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
