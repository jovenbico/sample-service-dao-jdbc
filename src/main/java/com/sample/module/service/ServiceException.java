package com.sample.module.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -53536395448651037L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
