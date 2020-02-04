package com.gmail.ramawthar.priyash.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "batched-transactions", type = "_doc")
public class BatchedTransaction {

	@Id
	private String id;
	private String tranDate;
	private String reference;
	private String account;
	private String categoryTree;   
	private BigDecimal amount; 
	private String bacth;
	
	
	public BatchedTransaction(String id, String tranDate, String reference, String account, String categoryTree,
			BigDecimal amount, String bacth) {
		super();
		this.id = id;
		this.tranDate = tranDate;
		this.reference = reference;
		this.account = account;
		this.categoryTree = categoryTree;
		this.amount = amount;
		this.bacth = bacth;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
