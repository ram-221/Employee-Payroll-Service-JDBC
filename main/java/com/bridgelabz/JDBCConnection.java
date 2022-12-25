package com.bridgelabz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBCConnection {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/employee_service?characterEncoding=UTF-8";//characterEncoding=UTF-8";
		String userName = "root";
		String password = "12345";
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath", e);
		}
		
		listDrivers();
		try {
			System.out.println("Connting to database"+jdbcURL);
			con = DriverManager.getConnection(jdbcURL,userName,password);
			System.out.println("Connection is succeccful"+con);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while(driverList.hasMoreElements()) {
			Driver driverClass = (Driver)driverList.nextElement();
			System.out.println(" "+driverClass.getClass().getName());
		}
	}
}
