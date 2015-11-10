package com.sample.module.dao;

import java.util.List;

import com.sample.module.domain.Account;

public interface AccountDAO {

	public void insert(Account account);
	
	public Account get(int id);

	public List<Account> findByUserId(int userId);
	
	public void update(Account account);
	
	
}
