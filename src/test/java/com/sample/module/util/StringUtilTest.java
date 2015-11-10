package com.sample.module.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void emailFomatTest() {
		
		@SuppressWarnings("serial")
		List<String> validEmails = new ArrayList<String>(){{
			add("jbico@yahoo.com");
			add("jbico-100@yahoo.com");
			add("jbico.100@yahoo.com");
		}};
		@SuppressWarnings("serial")
		List<String> invalidEmails = new ArrayList<String>(){{
			add("jbico");
			add("jbico@.com.my");
			add("jbico123@gmail.a");
		}};
		
		for (String email : validEmails) {
			assertTrue(
					StringUtil.isValidEmail(email)
					);
		}
		
		for (String email : invalidEmails) {
			assertFalse(
					StringUtil.isValidEmail(email)
					);
		}
		
	}
	
}
