package com.sample.module.model.validator;

import java.util.Map;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

import com.sample.module.model.RegisterForm;

public class RegisterFormTest {

	final Logger LOG = Logger.getLogger(getClass());
	
	@Test
	public void validator() {
		RegisterForm form = new RegisterForm();
		form.setEmail("joven@gmail.com");
		
		Map<String, String> errors = RegisterForm.VALIDATOR.validate(form);
		
		LOG.debug(errors);
		assertTrue(errors.isEmpty());
	}
	
}
