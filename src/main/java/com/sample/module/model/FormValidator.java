package com.sample.module.model;

import java.util.Map;

public interface FormValidator<T> {

	public Map<String, String> validate(T form);
	
}
