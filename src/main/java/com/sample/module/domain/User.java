package com.sample.module.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User implements Serializable {

	private static final long serialVersionUID = 5074712855329157739L;

	private Integer id;
	private String name;
	private String email;

	public User(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public static final ResultSetMapper<User> MAPPER = new ResultSetMapper<User>() {
		public User convert(ResultSet rs) throws SQLException {
			
			User user = new User(rs.getString("name"));
			user.id = rs.getInt("id");
			user.email = rs.getString("email");
			
			return user;
		}
	};
	
}
