package com.sample.module.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Account implements Serializable {

	private static final long serialVersionUID = -8910498816910186435L;

	private Integer id;
	private Integer userId;
	private double amount;

	public Account() {
	}
	
	public Account(Integer userId, double amount) {
		this.userId = userId;
		this.amount = amount;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static ResultSetMapper<Account> MAPPER = new ResultSetMapper<Account>() {
		public Account convert(ResultSet rs) throws SQLException {
			
			Account account = new Account(
					rs.getInt("user_id"), 
					rs.getDouble("amount")
					);
			account.id = rs.getInt("id");
			
			return account;
		}
	};
}
