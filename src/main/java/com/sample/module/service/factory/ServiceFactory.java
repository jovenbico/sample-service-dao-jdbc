package com.sample.module.service.factory;

import com.sample.module.service.UserService;

/**
 * @author joven.bico
 * create objects that implement Service interfaces
 */
public abstract class ServiceFactory {

	private static final ServiceFactory INSTANCE 
		= new ServiceFactoryManager();
	
	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public abstract UserService getUserService();
}
