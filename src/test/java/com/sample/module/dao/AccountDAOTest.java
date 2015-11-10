package com.sample.module.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.module.dao.factory.DAOFactory;
import com.sample.module.dao.jdbc.DBConnection;
import com.sample.module.domain.Account;

public class AccountDAOTest extends BaseDAOTest {

	private final Logger LOG = Logger.getLogger(getClass());
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
	private AccountDAO accountDAO = daoFactory.getAccountDAO();
	
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
			assertNotNull(accountDAO);
			
			Integer userId = 20;
			double amount = 5000;
			Account account = new Account(userId, amount);
			accountDAO.insert(account);

			LOG.debug(account);
			assertNotNull(account.getId());
			
		} catch (Exception e) {
			DBConnection.rollback();
		}
	}
	
	@Test
	public void get() {
		try {
			
			int id = 2;
			Account account = accountDAO.get(id);
			LOG.debug(account);
			assertNotNull(account);
			
		} catch (Exception e) {
			DBConnection.rollback();
		}
	}
	
	@Test
	public void findByUserId() {
		try {
			
			int userId = 20;
			List<Account> accounts = accountDAO.findByUserId(userId);
			
			LOG.debug(accounts);
			assertNotNull(accounts);
			assertFalse(accounts.isEmpty());
			
		} catch (Exception e) {
			DBConnection.rollback();
		}
	}
	
	@Test
	public void update() {
		try {
			
			Account account = accountDAO.get(4);
			double accountAmount = account.getAmount();
			double amount = 100;
			
			accountAmount += amount;
			account.setAmount(accountAmount);
			
			accountDAO.update(account);
			LOG.debug(account);
			
		} catch (Exception e) {
			DBConnection.rollback();
		}
	}
	
}
