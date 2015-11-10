package com.sample.module.service;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sample.module.dao.BaseDAOTest;
import com.sample.module.model.RegisterForm;
import com.sample.module.service.factory.ServiceFactory;

public class ServiceFactoryTest extends BaseDAOTest {

	final Logger LOG = Logger.getLogger(getClass());
	
//	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	@Test
	public void concurrency() throws InterruptedException {
		
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				LOG.debug("run...");
				UserService userService = ServiceFactory.getInstance().getUserService();
				
				RegisterForm form = new RegisterForm();
				form.setName("test101");
				form.setEmail("test101@gmail.com");
				try {
					userService.register(form);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				LOG.debug("run...");
				UserService userService = ServiceFactory.getInstance().getUserService();
				
				RegisterForm form = new RegisterForm();
				form.setName("test102");
				form.setEmail("test102@gmail.com");
				try {
					userService.register(form);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
		};
		
		thread1.start();
		thread2.start();
		
		Thread.sleep(2 * 1000);
		
//		UserService userService = serviceFactory.getUserService();
//		
//		RegisterForm form = new RegisterForm();
//		form.setName("test101");
//		form.setEmail("test101@gmail.com");
//		try {
//			userService.register(form);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
	}
	
}
