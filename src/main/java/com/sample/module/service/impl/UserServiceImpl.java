package com.sample.module.service.impl;

import org.apache.log4j.Logger;

import com.sample.module.dao.AccountDAO;
import com.sample.module.dao.UserDAO;
import com.sample.module.domain.Account;
import com.sample.module.domain.User;
import com.sample.module.model.RegisterForm;
import com.sample.module.model.TransferAmountForm;
import com.sample.module.service.ServiceException;
import com.sample.module.service.UserService;
import com.sample.module.service.factory.ServiceFactoryJdbc;

public class UserServiceImpl extends ServiceFactoryJdbc implements UserService {

	final Logger LOG = Logger.getLogger(getClass());
	
	private final UserDAO userDAO;
	private final AccountDAO accountDAO;
	
	public UserServiceImpl(UserDAO userDAO, AccountDAO accountDAO) {
		this.userDAO = userDAO;
		this.accountDAO = accountDAO;
	}
	
	@Override
	public void register(RegisterForm form) throws ServiceException {
		startTransaction();
		
		try {
			
			User user = userDAO.getByName(form.getName());
			if (user != null)
				throw new Exception("Name already exist");
			
			user = new User(form.getName());
			user.setEmail(form.getEmail());
			userDAO.insert(user);
			
		} catch (Exception e) {
			rollbackTransaction();
			throw new ServiceException(e);
		} finally {
			commitTransaction();
		}
		
	}
	
	@Override
	public void transfer(TransferAmountForm form) throws ServiceException {
		startTransaction();
		
		try {
			
			Account fromAccount = accountDAO.get(form.getFromAccountId());
			Account toAccount = accountDAO.get(form.getToAccountId());
			double fromAccountAmount = fromAccount.getAmount();
			double toAccountAmount = toAccount.getAmount();
			double amount = form.getAmount();
			
			if ( (fromAccountAmount-amount) < 0 ) {
				throw new ServiceException("insufficient funds");
			}
			
			fromAccountAmount -= amount;
			toAccountAmount += amount;
			
			fromAccount.setAmount(fromAccountAmount);
			toAccount.setAmount(toAccountAmount);
			
			accountDAO.update(fromAccount);
			accountDAO.update(toAccount);
			
		} catch (Exception e) {
			rollbackTransaction();
			throw new ServiceException(e);
		} finally {
			commitTransaction();
		}
		
	}

}
