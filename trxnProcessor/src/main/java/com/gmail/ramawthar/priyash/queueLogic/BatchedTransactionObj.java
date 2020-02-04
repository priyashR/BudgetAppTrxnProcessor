package com.gmail.ramawthar.priyash.queueLogic;

import java.math.BigDecimal;

public class BatchedTransactionObj {

	
	private String tranDate;
	private String reference;
	private String account;
	private String categoryTree;   
	private BigDecimal amount; 
	private String bacth;
	
	public BatchedTransactionObj() {
		super();
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(String categoryTree) {
		this.categoryTree = categoryTree;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBacth() {
		return bacth;
	}

	public void setBacth(String bacth) {
		this.bacth = bacth;
	}
	
	
}
