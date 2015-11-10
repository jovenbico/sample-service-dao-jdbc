package com.sample.module.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.sample.module.dao.jdbc.DBConnection;

public class DBConnectionTest extends BaseDAOTest {

	final Logger LOG = Logger.getLogger(getClass());
	
	@Test
	public void test() throws SQLException, InterruptedException {
		DBConnection.create();
		LOG.debug("create connection");
		
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				try {
					LOG.debug("thread1");
					Connection connection = DBConnection.current();
					if (connection!=null)
						LOG.debug(connection.isClosed());
				} catch (SQLException e) {
					LOG.error("err...", e);
				}
			}
		};
		thread1.start();
		
		Connection connection = DBConnection.current();
		Assert.assertFalse(connection.isClosed());
		DBConnection.close();
		Assert.assertTrue(connection.isClosed());
		
		Thread.sleep(2 * 1000);
	}
	
}
