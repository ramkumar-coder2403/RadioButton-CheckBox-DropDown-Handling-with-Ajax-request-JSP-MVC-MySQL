package com.nic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/demo?characterEncoding=latin1&useConfigs=maxPerformance";
	// demo?useSSL=false
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "12345";
	private static Connection con;

	public static Connection getCon() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
