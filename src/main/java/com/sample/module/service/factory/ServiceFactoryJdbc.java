package com.sample.module.service.factory;

import com.sample.module.dao.jdbc.DBConnection;

public abstract class ServiceFactoryJdbc {

	protected void startTransaction() {
		DBConnection.create();
	}
	
	protected void rollbackTransaction() {
		DBConnection.rollback();
	}
	
	protected void commitTransaction() {
		DBConnection.close();
	}
	
}
