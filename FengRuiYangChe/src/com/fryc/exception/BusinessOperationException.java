package com.fryc.exception;

public class BusinessOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// expKey will be used in MessageTranslater.java,
	// value must be from message.properties file
	private String expKey = null;
	
	
	public BusinessOperationException(String expKey) {
		super();
		this.setExpKey(expKey);
	}

	public BusinessOperationException(Throwable cause) {
		super(cause);
	}

	public String getExpKey() {
		return expKey;
	}

	public void setExpKey(String expKey) {
		this.expKey = expKey;
	}
	
}
