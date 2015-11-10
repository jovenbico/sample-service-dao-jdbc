package com.sample.module.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.module.dao.factory.DAOFactory;
import com.sample.module.dao.jdbc.DBConnection;
import com.sample.module.domain.User;

public class UserDAOTest extends BaseDAOTest {

	final Logger LOG = Logger.getLogger(getClass());
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private UserDAO userDAO = daoFactory.getUserDAO();
	
	@Before
	public void before() {
		DBConnection.create();
	}
	
	@After
	public void after() {
		DBConnection.close();
	}
	
	@Test
	public void insert() {
		try {
			assertNotNull(userDAO);
			
			User user = new User("joven");
			user.setEmail("joven@gmail.com");
			
			LOG.debug(user);
			userDAO.insert(user);
			LOG.debug(user);
			
			assertNotNull(user.getId());
		} catch (Exception e) {
			DBConnection.rollback();
		}
		
	}
	
	@Test
	public void mockInsert() {
		UserDAO userDAO = mock(UserDAO.class);
		User user = new User("joven-mock");
		user.setEmail("joven@gmail.com");
		user.setId(101);
		when(userDAO.getByName("joven-mock")).thenReturn(user);
		
		LOG.debug(user);
		
		assertNotNull(userDAO.getByName("joven-mock"));
		assertNull(userDAO.getByName("joven"));
	}
	
}
