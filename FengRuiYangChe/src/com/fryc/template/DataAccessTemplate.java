package com.fryc.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fryc.dao.RowMapper;
import com.fryc.exception.BusinessOperationException;
import com.fryc.util.DBUtil;

public class DataAccessTemplate {

	/**
	 * Execute sinlge one SQL for INSERT / UPDATE / DELETE
	 * 
	 * @param sql	SQL to be executed, it can be one completed SQL string or one SQL with placeholder "?"
	 * @param params 
	 * 			if SQL is with "?", this parameter must be given, 
	 * 			also the order in the array must be the same with placeholder's order in SQL
	 */
	public void executeUpdate(String sql, List<Object> params) {
		validateParam(sql, params);
		
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new BusinessOperationException("CONNECTION_OPEN");
		}
		
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			if (params != null) {
				int paramCount = params.size();
				for (int k=0; k<paramCount; k++) {
					ps.setObject(k + 1, params.get(k));
				}
			}
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			throw new BusinessOperationException(e);
		} finally {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			
			try {
				DBUtil.release(null, ps, connection);
			} catch (SQLException e) {
				throw new BusinessOperationException("SQL_CLOSE");
			}
		}
	}
	
	/**
	 * Execute multiple SQL in single one transaction for INSERT / UPDATE / DELETE
	 * 
	 * @param sqlParamMap
	 * 			This is one {@link java.util.LinkedHashMap}: </br>
	 * 			1) Key in the map: SQL to be executed </br>
	 * 			2) Value in the map: An array to hold parameters for SQL to use
	 *
	 */
	public void executeUpdate(LinkedHashMap<String, List<Object>> sqlParamMap) {
		if (sqlParamMap == null || sqlParamMap.isEmpty())
			throw new BusinessOperationException("SQL_INVALID");
		
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new BusinessOperationException("CONNECTION_OPEN");
		}
		
		PreparedStatement ps = null;
		try {
			for (Map.Entry<String, List<Object>> entry : sqlParamMap.entrySet()) {
				String sql = entry.getKey();
				List<Object> params = entry.getValue();
				
				validateParam(sql, params);
				
				ps = connection.prepareStatement(sql);
				if (params != null) {
					int paramCount = params.size();
					for (int k=0; k<paramCount; k++) {
						ps.setObject(k + 1, params.get(k));
					}
				}
				ps.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			throw new BusinessOperationException(e);
		} finally {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			
			try {
				DBUtil.release(null, ps, connection);
			} catch (SQLException e) {
				throw new BusinessOperationException("SQL_CLOSE");
			}
		}
	}
	
	
	/**
	 * Execute multiple SQL in a batch for INSERT / UPDATE / DELETE
	 * 
	 * @param sqlParamMap
	 * 			This is one {@link java.util.LinkedHashMap}: </br>
	 * 			1) Key in the map: SQL to be executed </br>
	 * 			2) Value in the map: An array to hold parameters for SQL to use
	 *
	 */
	public void executeBatchUpdate(Map<String, List<Object>> sqlParamMap) {
		if (sqlParamMap == null || sqlParamMap.isEmpty())
			throw new BusinessOperationException("SQL_INVALID");
		
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new BusinessOperationException("CONNECTION_OPEN");
		}
		
		PreparedStatement ps = null;
		try {
			for (Map.Entry<String, List<Object>> entry : sqlParamMap.entrySet()) {
				String sql = entry.getKey();
				List<Object> params = entry.getValue();
				
				validateParam(sql, params);
				
				ps = connection.prepareStatement(sql);
				if (params != null) {
					int paramCount = params.size();
					for (int k=0; k<paramCount; k++) {
						ps.setObject(k + 1, params.get(k));
					}
				}
				ps.addBatch();
			}
			
			ps.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			throw new BusinessOperationException(e);
		} finally {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BusinessOperationException(e1);
			}
			try {
				DBUtil.release(null, ps, connection);
			} catch (SQLException e) {
				throw new BusinessOperationException("SQL_CLOSE");
			}
		}
	}

	/**
	 * Qxecute one query, return a object list based on the gived row mapper
	 * 
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public <T> List<T> executeQuery(String sql, List<Object> params, RowMapper<T> rowMapper) {
		validateParam(sql, params);
		
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
		} catch (SQLException e) {
			throw new BusinessOperationException("CONNECTION_OPEN");
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			if (params != null) {
				int paramCount = params.size();
				for (int k=0; k<paramCount; k++) {
					ps.setObject(k + 1, params.get(k));
				}
			}
			
			rs = ps.executeQuery();
			List<T> resultList = new ArrayList<T>();
			while (rs.next()) {
				resultList.add(rowMapper.mapRow(rs));
			}
			return resultList;
		} catch (SQLException e) {
			throw new BusinessOperationException(e);
		} finally {
			try {
				DBUtil.release(rs, ps, connection);
			} catch (SQLException e) {
				throw new BusinessOperationException("SQL_CLOSE");
			}
		}
	}
	
	/**
	 * Qxecute one query, return a map list,
	 * in the returned map, key is column name from the result set,
	 * value is column value from the result set
	 * 
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> executeQuery(String sql, List<Object> params) {
		validateParam(sql, params);
		
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
		} catch (SQLException e) {
			throw new BusinessOperationException("CONNECTION_OPEN");
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			if (params != null) {
				int paramCount = params.size();
				for (int k=0; k<paramCount; k++) {
					ps.setObject(k + 1, params.get(k));
				}
			}
			
			rs = ps.executeQuery();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(100);
			// TODO
			return mapList;
		} catch (SQLException e) {
			throw new BusinessOperationException(e);
		} finally {
			try {
				DBUtil.release(rs, ps, connection);
			} catch (SQLException e) {
				throw new BusinessOperationException("SQL_CLOSE");
			}
		}
	}
	
	private void validateParam(String sql, List<Object> params) {
		if (sql == null || sql.trim().length() == 0)
			throw new BusinessOperationException("SQL_INVALID");
		
		if (sql.contains("?") && (params == null || params.isEmpty()))
			throw new BusinessOperationException("SQL_INVALID_PARAM");
	}
	
}
