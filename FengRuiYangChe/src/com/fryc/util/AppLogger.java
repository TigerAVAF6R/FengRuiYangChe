package com.fryc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;


final public class AppLogger {
	
	static final Logger appLogger = Logger.getLogger(AppLogger.class.getClass());

	/**
	 * Use this method to log enter event to a method
	 * 
	 * @param className
	 * @param methodName
	 */
	public static void enterMethod(String className, String methodName) {
		appLogger.info("Entering method. Classname: " + className + ": Method: " + methodName);
	}

	/**
	 * Use this method to log exit event to a method
	 * 
	 * @param className
	 * @param methodName
	 */
	public static void exitMethod(String className, String methodName) {
		appLogger.info("Exiting method. Classname: " + className + ": Method: " + methodName);
	}

	/**
	 * Info logging.
	 * 
	 * @param className
	 * @param methodName
	 * @param message
	 */
	public static void logInfo(String className, String methodName, String message) {
		appLogger.info(" Classname: " + className + ". Method: " + methodName + " " + message);
	}

	/**
	 * for error logs
	 * 
	 * @param className
	 * @param methodName
	 * @param message
	 * @param e
	 */
	public static void logError(String className, String methodName, String message, Throwable e) {
		appLogger.error(" Classname: " + className + ". Method : " + methodName + " " + message, e);
	}
	
	public static void logDebugSQLQuery(String content) {
		appLogger.debug("SQL query: \n" + content + "\n");
	}
	
	public static void logWebServiceStarted(String method, String uri) {
		appLogger.info(" Start web service call, HTTP Method: " + method + ", URI: " + uri);
	}
	
	public static void logWebServiceEnded(String method, String uri) {
		appLogger.info(" End web service call, HTTP Method: " + method + ", URI: " + uri);
	}
	
	public static void logWebServiceQueryString(String queryString) {
		if (queryString != null) {
			String s = null;
			try {
				s = URLDecoder.decode(queryString, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			appLogger.info(" Web service query string: " + (queryString.trim().length() == 0 ? "Empty query string" : s));
		}
	}
	
}
