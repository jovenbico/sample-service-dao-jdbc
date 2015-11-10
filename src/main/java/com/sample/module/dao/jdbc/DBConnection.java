package com.sample.module.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.sample.module.util.JdbcUtil;

/**
 * @author joven.bico
 * provides thread-local: JDBC Connection
 */
public class DBConnection {

	private final static Logger LOG = Logger.getLogger(DBConnection.class);
	private final static ThreadLocal<DBConnection> threadLocal 
		= new ThreadLocal<DBConnection>();
	
	private Connection connection;
	
	private DBConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static void create() {
		try {
			DataSource dataSource = JdbcUtil.getDataSource();
			Connection connection = dataSource.getConnection();
			DBConnection dbConnection = new DBConnection(connection);
			threadLocal.set(dbConnection);
		} catch (SQLException | NullPointerException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public static Connection current() {
		try {
			DBConnection dbConnection = threadLocal.get();
			Connection connection = dbConnection.connection;
			return connection;
		} catch (NullPointerException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static void rollback() {
		try {
			DBConnection dbConnection = threadLocal.get();
			Connection connection = dbConnection.connection;
			connection.rollback();
		} catch (SQLException | NullPointerException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public static void close() {
		try {
			DBConnection dbConnection = threadLocal.get();
			Connection connection = dbConnection.connection;
			connection.commit();
			connection.close();
			
			threadLocal.remove();
		} catch (SQLException | NullPointerException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
