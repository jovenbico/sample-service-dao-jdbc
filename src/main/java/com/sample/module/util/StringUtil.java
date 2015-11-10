package com.sample.module.util;

import java.util.regex.Pattern;

public class StringUtil {

	private static final Pattern PATTERN_EMAIL 
		= Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	private StringUtil() {
	}
	
	public static boolean isValidEmail(String email) {
		boolean result = false;
		
		result = PATTERN_EMAIL.matcher(email).matches();
		
		return result;
	}
	
}
