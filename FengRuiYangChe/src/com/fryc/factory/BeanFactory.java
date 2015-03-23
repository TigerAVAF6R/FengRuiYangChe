package com.fryc.factory;

/**
 * This is a factory where will produce all the bean objects,
 * all the object will be in Singleton mode.
 * 
 * @author tiger
 *
 */
public class BeanFactory {

	private static String configFile = null;
	private static ClassPathXMLApplicationContext applicationContext = null;
	
	
	private BeanFactory() {}
	
	public static void initialize() throws ExceptionInInitializerError {
		try {
			applicationContext = null;
			applicationContext = new ClassPathXMLApplicationContext(configFile);
		} catch (Throwable thrown) {
			throw new ExceptionInInitializerError(thrown);
		}
	}
	
	public static void destroy() throws ExceptionInInitializerError {
		try {
			applicationContext.destroy();
			configFile = null;
			applicationContext = null;
		} catch (Throwable thrown) {
			throw new ExceptionInInitializerError(thrown);
		}
	}
	
	public static void setConfigFile(String configFile) {
		BeanFactory.configFile = configFile;
	}

	public static <T> T getBean(String beanId, Class<T> clazz) {
		if (applicationContext == null)
			initialize();
		return (T)applicationContext.getBean(beanId, clazz);
	}
	
}
