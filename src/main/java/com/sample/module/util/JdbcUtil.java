package com.sample.module.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcUtil {

	static final Logger LOG = Logger.getLogger(JdbcUtil.class);
	private static DataSource dataSource;
	
	static {
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/myDatasource");
		} catch (NamingException e) {
//			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	private JdbcUtil() {}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static void close(Statement... statements) {
		for (Statement statement : statements) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	
	public static void close(ResultSet...resultSets) {
		for (ResultSet resultSet : resultSets) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	
}
