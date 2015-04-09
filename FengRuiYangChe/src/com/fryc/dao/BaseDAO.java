package com.fryc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface BaseDAO {

	JdbcTemplate getJdbcTemplate() throws Exception;
	
	NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() throws Exception;
	
	String getNewUUID() throws Exception;
	
}
