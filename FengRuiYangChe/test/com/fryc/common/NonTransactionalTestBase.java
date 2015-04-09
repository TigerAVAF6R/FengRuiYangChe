package com.fryc.common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={
		"file:WebContent/WEB-INF/spring-config/applicationContext_bean.xml",
		"file:WebContent/WEB-INF/spring-config/applicationContext_ds.xml",
		"file:WebContent/WEB-INF/spring-config/applicationContext_transaction.xml"})
public class NonTransactionalTestBase extends AbstractJUnit4SpringContextTests {
	
}
