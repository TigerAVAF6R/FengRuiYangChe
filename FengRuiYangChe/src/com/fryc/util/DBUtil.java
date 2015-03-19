package com.fryc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class DBUtil {

	private static DataSource dataSource = null;
	private static String DB_DRIVER = null;
	private static String DB_URL = null;
	private static String DB_USER = null;
	private static String DB_PWD = null;
	
	static {
		try {
			Map<String, String> map = PropertiesUtil.getPropertyMap("CONNECTION_POOL_USED", "DB_DRIVER_NAME", "DB_URL", "USER_NAME", "USER_PWD");
			String isPoolUsed = map.get("CONNECTION_POOL_USED");
			if (isPoolUsed != null && "T".equals(isPoolUsed)) {
				initializeConnectionPool();
			} else {
				DB_DRIVER = map.get("DB_DRIVER_NAME");
				DB_URL = map.get("DB_URL");
				DB_USER = map.get("USER_NAME");
				DB_PWD = map.get("USER_PWD");
				Class.forName(DB_DRIVER);
			}
		} catch (Throwable thrown) {
			throw new ExceptionInInitializerError(thrown);
		}
	}

	/**
	 * Initialize connection pool based on db.properties file
	 */
	private static void initializeConnectionPool() {
		try {
			
		} catch (Throwable thrown) {
			throw new ExceptionInInitializerError(thrown);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		if (dataSource != null) {
			conn = dataSource.getConnection();
		} else {
			String jndiName = PropertiesUtil.getProperty("JNDI_DATA_SOURCE");
			if (jndiName != null && jndiName.trim().length() > 0) {
				initializeConnectionPool();
				if (dataSource != null) {
					conn = dataSource.getConnection();
				} else {
					conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				}
			}
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		}
		return conn;
	}
	
	public static void release(ResultSet rs, Statement statement, Connection connection) throws SQLException {
		try {
			if (rs != null)
				rs.close();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection connection = getConnection();
		System.out.println(connection);
		connection.close();
		boolean isClosed = connection.isClosed();
		System.out.println("connection.isClosed(): " + isClosed);
	}
	
}
