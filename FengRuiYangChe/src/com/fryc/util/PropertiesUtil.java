package com.fryc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
	
	/**
	 * Especially for values from message.properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessageProperty(String key) {
		String value = null;
		if (key != null) {
			InputStream inStream = null;
			try {
				Properties props = new Properties();
				inStream = PropertiesUtil.class.getResourceAsStream(AppConstant.PROPERTY_PATH_MESSAGE);
				props.load(inStream);
				value = props.getProperty(key);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException e) {
						throw new ExceptionInInitializerError(e);
					}
				}
			}
		}
		return value;
	}
	
	public static String getProperty(String key) {
		String value = null;
		if (key != null) {
			InputStream inStream = null;
			try {
				Properties props = new Properties();
				inStream = PropertiesUtil.class.getResourceAsStream(AppConstant.PROPERTY_PATH);
				props.load(inStream);
				value = props.getProperty(key);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException e) {
						throw new ExceptionInInitializerError(e);
					}
				}
			}
		}
		return value;
	}
	
	/**
	 * Return a map based on the given key list,
	 * in the map, key will be given key, value will be property value
	 * 
	 * @param key
	 * @return Map<String, String>
	 */
	public static Map<String, String> getPropertyMap(String... key) {
		Map<String, String> map = new HashMap<String, String>();
		if (key != null) {
			InputStream inStream = null;
			try {
				Properties props = new Properties();
				inStream = PropertiesUtil.class.getResourceAsStream(AppConstant.PROPERTY_PATH);
				props.load(inStream);
				for (String s : key) {
					String value = props.getProperty(s);
					map.put(s, value);
				}
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException e) {
						throw new ExceptionInInitializerError(e);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * Return all values into a map based on the given property file,
	 * in the map, key will be key, value will be property value
	 * 
	 * @param key
	 * @return Map<String, String>
	 */
	public static Map<String, String> getAllProperty(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inStream = null;
		try {
			Properties props = new Properties();
			inStream = PropertiesUtil.class.getResourceAsStream(filePath);
			props.load(inStream);
			Set<Entry<Object, Object>> entrySet = props.entrySet();
			if (entrySet != null) {
				for (Entry<Object, Object> entry : entrySet) {
					String id = (String)entry.getKey();
					String value = (String)entry.getValue();
					map.put(id, value);
				}
			}
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					throw new ExceptionInInitializerError(e);
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String key = "DB_URL";
		String result = getProperty(key);
		System.out.println(result);
		
		Map<String, String> map = getPropertyMap("DB_DRIVER_NAME", "DB_URL", "BD_API_KEY", "BD_API_SECRET");
		System.out.println(map);
	}

}
