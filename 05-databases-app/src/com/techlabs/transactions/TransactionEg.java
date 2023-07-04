package com.techlabs.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionEg {
	private static Connection connection = null;
	private static Statement statement;
	
	static void connect() {
		try {
			if(connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ORGANIZATION","root","Praveen7ye");
				System.out.println("Connecton Established");
			}
			else {
			System.out.println("Connecton Already Established");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void execute() {
		Savepoint savepoint1 = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			statement.executeUpdate("insert into regions (REGION_ID,REGION_NAME) value(6, 'Antarctic')");
			savepoint1 = connection.setSavepoint("Savepoint1");
			statement.executeUpdate("UPDATE regions SET region_name= Antarctica  WHERE region_id=6");
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
			if (connection != null) {
		        try {
		            connection.rollback(savepoint1);
		            System.out.println("Transaction rolled back.");
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		} finally {
			try {
				connection.setAutoCommit(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public static void main(String[] args) {
		connect();
		execute();
	}

}
