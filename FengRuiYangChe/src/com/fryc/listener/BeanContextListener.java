package com.fryc.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fryc.factory.BeanFactory;
import com.fryc.util.AppConstant;

/**
 * Application Lifecycle Listener implementation class BeanContextListener
 *
 */
public class BeanContextListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {
    	BeanFactory.destroy();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String configFile = sc.getInitParameter(AppConstant.CONTEXT_INIT_PARAM_BEANCONFIG);
    	BeanFactory.setConfigFile(configFile);
    	BeanFactory.initialize();
    }
	
}
