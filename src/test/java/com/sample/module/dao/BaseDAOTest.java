package com.sample.module.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.BeforeClass;

public abstract class BaseDAOTest {

	@BeforeClass
	public static void beforeClass() throws NamingException
	{
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext initialContext = new InitialContext();

		initialContext.createSubcontext("java:");
		initialContext.createSubcontext("java:comp");
		initialContext.createSubcontext("java:comp/env");
		initialContext.createSubcontext("java:comp/env/jdbc");

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setUrl("jdbc:mysql://localhost:3306/demo_db");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setDefaultAutoCommit(false);

		initialContext.bind("java:comp/env/jdbc/myDatasource", dataSource);
	}

}
