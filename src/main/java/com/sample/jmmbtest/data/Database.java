package com.sample.jmmbtest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {	
	private String connectionUrl = "";
	private static Database database;

	private Database() {
		String jdbcDriver;
		
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
            jdbcDriver = resourceBundle.getString("jdbc.driver");
            connectionUrl = resourceBundle.getString("jdbc.connectionUrl");
            Class.forName(jdbcDriver);            
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (database == null) {
			database = new Database();
		}

		try {
			return DriverManager.getConnection(database.connectionUrl);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}