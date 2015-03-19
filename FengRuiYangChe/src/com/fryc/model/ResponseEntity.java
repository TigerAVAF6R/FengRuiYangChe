package com.fryc.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fryc.util.AppConstant;

public class ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String successFlag;
	private String message;
	private Map<String, Object> data;
	
	
	public ResponseEntity() {
	}
	
	public ResponseEntity(String successFlag, String message, Map<String, Object> data) {
		this.successFlag = successFlag;
		this.message = message;
		this.data = data;
	}

	public ResponseEntity(String successFlag, String message) {
		this.successFlag = successFlag;
		this.message = message;
	}

	public String getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public static synchronized ResponseEntity getDefaultEntity() {
		return new ResponseEntity(AppConstant.RESPONSE_FLAG_SUCCESS, AppConstant.RESPONSE_MSG_DEFAULT_SUCCESS, null);
	}
	
	public synchronized void addDataEntry(String key, Object obj) {
		if (this.data == null)
			this.data = new LinkedHashMap<String, Object>();
		
		this.data.put(key, obj);
	}
	
}
