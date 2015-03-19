package com.fryc.util;

import com.fryc.exception.BusinessOperationException;

public class MessageTranslater {

	public synchronized static String translateExp(Throwable thrown) {
		String result = null;
		if (thrown instanceof BusinessOperationException) {
			BusinessOperationException exp = (BusinessOperationException) thrown;
			String expKey = exp.getExpKey();
			if (expKey != null && expKey.trim().length() > 0) {
				result = PropertiesUtil.getMessageProperty(expKey);
			} else {
				result = exp.getMessage();
			}
		} else {
			Exception e = (Exception) thrown;
			result = e.getMessage();
		}
		return result;
	}

}
