package com.sample.module.dao.factory;

import com.sample.module.dao.AccountDAO;
import com.sample.module.dao.UserDAO;

/**
 * @author joven.bico
 * create objects that implement DAO interfaces
 */
public abstract class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactoryJdbcManager();
	public static DAOFactory getInstance() {
		return INSTANCE;
	}
	
	public abstract UserDAO getUserDAO();
	public abstract AccountDAO getAccountDAO();

}
