package com.fryc.util;

public interface AppConstant {
	
	String CONTEXT_INIT_PARAM_BEANCONFIG = "CONTEXT_CONFIG_FILE";

	String REQUEST_CREATE = "C"; // insert
	String REQUEST_READ = "R"; // load data
	String REQUEST_UPDATE = "U"; // update
	String REQUEST_DELETE = "D"; // delete
	
	String RESPONSE_FLAG_SUCCESS = "T";
	String RESPONSE_FLAG_ERROR = "F";
	String RESPONSE_MSG_DEFAULT_SUCCESS = "Success";
	String RESPONSE_MSG_DEFAULT_ERROR = "Error";
	String RESPONSE_DEFAULT_CONTENT_TYPE = "application/json";
	String RESPONSE_DEFAULT_JSON_KEY = "ResultSet";
	
	String PATTERN_MDY = "MM/dd/yyyy";
	String PATTERN_MDYHMS = "MM/dd/yyyy HH:mm:ss";
	
	String PROPERTY_PATH = "/db.properties";
	String PROPERTY_PATH_MESSAGE = "/message.properties";
	
}
