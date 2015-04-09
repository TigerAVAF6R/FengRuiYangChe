package com.fryc.common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations={
		"file:WebContent/WEB-INF/spring-config/applicationContext_bean.xml",
		"file:WebContent/WEB-INF/spring-config/applicationContext_ds.xml",
		"file:WebContent/WEB-INF/spring-config/applicationContext_transaction.xml"})
@TransactionConfiguration(
		transactionManager=TransactionalTestBase.TRANSACTION_MANAGER,
		defaultRollback=TransactionalTestBase.DEFAULT_ROLLBACK )
public class TransactionalTestBase extends AbstractTransactionalJUnit4SpringContextTests {
	
	public static final String TRANSACTION_MANAGER = "transactionManager";
	
	public static final boolean DEFAULT_ROLLBACK = false;
	
}
