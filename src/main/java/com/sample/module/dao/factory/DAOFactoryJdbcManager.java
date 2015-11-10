package com.sample.module.dao.factory;

import org.apache.log4j.Logger;

import com.sample.module.dao.AccountDAO;
import com.sample.module.dao.UserDAO;
import com.sample.module.dao.jdbc.AccountDAOJdbc;
import com.sample.module.dao.jdbc.UserDAOJdbc;

/**
 * @author joven.bico
 * create objects(Jdbc implementation) of DAO interfaces
 */
public class DAOFactoryJdbcManager extends DAOFactory {
	final Logger LOG = Logger.getLogger(getClass());
	
	private UserDAO userDAO;
	private AccountDAO accountDAO;

	@Override
	public UserDAO getUserDAO() {
		
		if (userDAO == null)
			userDAO = new UserDAOJdbc();
		
		return userDAO;
	}

	@Override
	public AccountDAO getAccountDAO() {
		
		if (accountDAO == null)
			accountDAO = new AccountDAOJdbc();
		
		return accountDAO;
	}
	
}
