package com.sample.module.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.module.dao.AccountDAO;
import com.sample.module.dao.UserDAO;
import com.sample.module.dao.factory.DAOFactory;
import com.sample.module.domain.Account;
import com.sample.module.domain.User;
import com.sample.module.model.RegisterForm;
import com.sample.module.model.TransferAmountForm;
import com.sample.module.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends BaseServiceTest {

	final Logger LOG = Logger.getLogger(getClass());
	
	@Mock
	private DAOFactory daoFactory;
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private AccountDAO accountDAO;
	
	
	@Before
	public void before() {
		when(daoFactory.getUserDAO()).thenReturn(userDAO);
		when(daoFactory.getAccountDAO()).thenReturn(accountDAO);
	}
	
	@Test
	public void registerForm() throws ServiceException {
//		start Mocking...
		when(userDAO.getByName("joven-ok")).thenReturn(null);
		when(userDAO.getByName("joven-fail")).thenReturn(new User("joven-fail"));
//		end Mocking...
		
		UserService userService = new UserServiceImpl(userDAO, accountDAO);
		
		RegisterForm formOk = new RegisterForm();
		formOk.setName("joven-ok");
		RegisterForm formFail = new RegisterForm();
		formFail.setName("joven-fail");
		
		userService.register(formOk);
		try {
			userService.register(formFail);
		} catch (ServiceException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void transferAmountFormOK() throws ServiceException {
//		start Mocking...
		Account fromAccount = new Account();
		fromAccount.setAmount(1000);
		Account toAccount = new Account();
		toAccount.setAmount(1000);
		
		when(accountDAO.get(4)).thenReturn(fromAccount);
		when(accountDAO.get(5)).thenReturn(toAccount);
//		end Mocking...
		
		UserService userService = new UserServiceImpl(userDAO, accountDAO);
		
		TransferAmountForm form = new TransferAmountForm();
		form.setFromAccountId(4);
		form.setToAccountId(5);
		form.setAmount(100);
		
		userService.transfer(form);
	}
	
	@Test(expected = ServiceException.class)
	public void transferAmountFormFail() throws ServiceException {
//		start Mocking...
		Account fromAccount = new Account();
		fromAccount.setAmount(1000);
		Account toAccount = new Account();
		toAccount.setAmount(1000);
		
		when(accountDAO.get(4)).thenReturn(fromAccount);
		when(accountDAO.get(5)).thenReturn(toAccount);
//		end Mocking...
		
		UserService userService = new UserServiceImpl(userDAO, accountDAO);
		
		TransferAmountForm form = new TransferAmountForm();
		form.setFromAccountId(4);
		form.setToAccountId(5);
		form.setAmount(5000);
		
		userService.transfer(form);
	}
	
}
