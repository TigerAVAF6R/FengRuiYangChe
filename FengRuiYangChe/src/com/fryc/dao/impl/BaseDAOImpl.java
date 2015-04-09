package com.fryc.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fryc.dao.BaseDAO;

@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return jdbcTemplate;
	}

	@Override
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
			throws Exception {
		return namedParameterJdbcTemplate;
	}

	@Override
	public String getNewUUID() throws Exception {
		return UUID.randomUUID().toString();
	}

}
