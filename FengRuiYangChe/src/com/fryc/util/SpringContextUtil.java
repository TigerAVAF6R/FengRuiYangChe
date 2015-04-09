package com.fryc.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * A utility class about Spring.
 * This can be used to obtain a bean in spring container.
 * 
 * @author Tiger
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, ServletContextAware {

    private static ApplicationContext applicationContext;  
    private static ServletContext servletContext;
  
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {  
        SpringContextUtil.applicationContext = applicationContext;  
    }  
    
    @Override
	public void setServletContext(ServletContext sc) {
    	SpringContextUtil.servletContext = sc;
	}

	public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {  
        return applicationContext.getBean(name, clazz);  
    }

	public static ServletContext getServletContext() {
		return servletContext;
	}  

}