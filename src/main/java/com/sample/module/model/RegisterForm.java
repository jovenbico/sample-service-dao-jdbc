package com.sample.module.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.sample.module.util.StringUtil;

public class RegisterForm implements Serializable {

	private static final long serialVersionUID = -5269147235831946380L;

	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static FormValidator<RegisterForm> VALIDATOR = new FormValidator<RegisterForm>() {
		@Override
		public Map<String, String> validate(RegisterForm form) {
			Map<String, String> errors = new HashMap<String, String>();
			
			String email = form.email;
			if (!StringUtil.isValidEmail(email))
				errors.put("INVALID_EMAIL", "Invalid email");
			
			return errors;
		}
	};

}
