package com.sample.module.service.factory;

import org.apache.log4j.Logger;

import com.sample.module.dao.factory.DAOFactory;
import com.sample.module.service.UserService;
import com.sample.module.service.impl.UserServiceImpl;

/**
 * @author joven.bico
 * create objects(Service implementation) of Service interfaces
 * manage DI
 */
public class ServiceFactoryManager extends ServiceFactory {

	final Logger LOG = Logger.getLogger(getClass());
	
	private DAOFactory daoFactory;
	public ServiceFactoryManager() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
	private UserService userService;
	@Override
	public UserService getUserService() {
		
		if (userService == null) {
			userService = new UserServiceImpl(
					daoFactory.getUserDAO(), 
					daoFactory.getAccountDAO()
					);
		}
		
		return userService;
	}
}
