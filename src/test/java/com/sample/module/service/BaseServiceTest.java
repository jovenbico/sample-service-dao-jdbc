package com.sample.module.service;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import com.mysql.jdbc.Connection;

public abstract class BaseServiceTest {

	@BeforeClass
	public static void beforeClass() throws NamingException, SQLException {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext initialContext = new InitialContext();

		initialContext.createSubcontext("java:");
		initialContext.createSubcontext("java:comp");
		initialContext.createSubcontext("java:comp/env");
		initialContext.createSubcontext("java:comp/env/jdbc");

		BasicDataSource dataSource = Mockito.mock(BasicDataSource.class);
		Connection connection = Mockito.mock(Connection.class);
		Mockito.when(dataSource.getConnection()).thenReturn(connection);

		initialContext.bind("java:comp/env/jdbc/myDatasource", dataSource);
	}
}
