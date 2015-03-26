package com.fryc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;

public final class DBUtil {

	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();
	
	/*
	 * if proxool is initialized successfully, then set isConnPoolInitialized=true,
	 * new connection will be from the connection pool
	 */
	private static boolean isConnPoolInitialized = false;
	private static String connectionPoolAlias = null;
	private static final String aliasPrefix = "proxool.";
	
	/*
	 * If connection pool is not initialized, will get direct JDBC connection 
	 */
	private static String DB_DRIVER = null;
	private static String DB_URL = null;
	private static String DB_USER = null;
	private static String DB_PWD = null;
	
	static {
		try {
			Map<String, String> map = PropertiesUtil.getPropertyMap("CONNECTION_POOL_USED", "DB_DRIVER_NAME", "DB_URL", "USER_NAME", "USER_PWD");
			String isPoolUsed = map.get("CONNECTION_POOL_USED");
			if ("T".equals(isPoolUsed)) {
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
			Properties properties = new Properties();
			properties.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));  
			PropertyConfigurator.configure(properties);
			connectionPoolAlias = PropertiesUtil.getProperty("jdbc-fryc.proxool.alias");
			isConnPoolInitialized = true;
		} catch (Throwable thrown) {
			throw new ExceptionInInitializerError(thrown);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = connThreadLocal.get();
		if (conn != null) {
			return conn;
		} else {
			if (isConnPoolInitialized && connectionPoolAlias != null) {
				conn = DriverManager.getConnection(aliasPrefix + connectionPoolAlias);
			} else {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			}
			connThreadLocal.set(conn);
			return conn;
		}
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
					connThreadLocal.remove();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		System.out.println(connection);
		connection.close();
		boolean isClosed = connection.isClosed();
		System.out.println("connection.isClosed(): " + isClosed);
	}
	
}
