package com.sample.module.dao;

import com.sample.module.domain.User;

public interface UserDAO {

	public void insert(User user);
	
	public User getByName(String name);
}
