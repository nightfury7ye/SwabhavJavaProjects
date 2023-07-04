package com.techlabs.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBconnection {
	private static DBconnection dBconnection;
	private Connection connection = null;
	private Statement statement;
	private DBconnection() {
		
	}
	public static DBconnection getDBconnection() {
		if(dBconnection == null)
			dBconnection = new DBconnection();
		return dBconnection;
	}
	
	void connect() {
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
	
	void execute(String query) {
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("region_id")+ "\t" + resultSet.getString("region_name")); 
			} 
			resultSet.close(); 
			statement.close(); 
		} catch (Exception e) { 
			e.printStackTrace();
		} 
	} 
	
	void insertRegion() throws SQLException { 
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter region ID and region Name"); 
		int regId = scanner.nextInt();
		String regName = scanner.next();
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into regions value(?,?)");
		preparedStatement.setInt(1, regId);
		preparedStatement.setString(2, regName);
		preparedStatement.executeUpdate();
		
		System.out.println("Record inserted");
		scanner.close();
	}
	void updateRegion() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter updated region Name and specify region_id on which update should be performed");
		String regName = scanner.next();
		int regId = scanner.nextInt();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE regions SET region_name=? WHERE region_id=?");
		preparedStatement.setString(1, regName);
		preparedStatement.setInt(2, regId);
		preparedStatement.executeUpdate();
		System.out.println("Record Updated");
		scanner.close();
	}
	void deleteRegion() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter region ID you want to delete");
		int regId = scanner.nextInt();
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM regions WHERE region_id=?");
		preparedStatement.setInt(1, regId);
		preparedStatement.executeUpdate();
		System.out.println("Record Deleted");
		scanner.close();
	}
	
}
