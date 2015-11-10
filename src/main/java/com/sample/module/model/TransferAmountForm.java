package com.sample.module.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TransferAmountForm implements Serializable {

	private static final long serialVersionUID = 5405340215519659037L;

	private Integer fromAccountId;
	private Integer toAccountId;
	private double amount;

	public Integer getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Integer fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Integer getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Integer toAccountId) {
		this.toAccountId = toAccountId;
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
}
