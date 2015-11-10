package com.sample.module.service;

import com.sample.module.model.RegisterForm;
import com.sample.module.model.TransferAmountForm;

public interface UserService {

	void register(RegisterForm form) throws ServiceException;
	
	void transfer(TransferAmountForm form) throws ServiceException;
	
}
