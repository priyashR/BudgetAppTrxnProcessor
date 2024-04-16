package com.gmail.ramawthar.priyash.queueLogic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.StringTokenizer;

import com.gmail.ramawthar.priyash.model.BatchedTransaction;
import com.gmail.ramawthar.priyash.model.Transaction;
import com.gmail.ramawthar.priyash.service.BatchedTransactionService;
import org.springframework.web.client.RestTemplate;

public class ProcessBatchedTransactions {
	String transactionLine;
	BatchedTransactionObj batchedTransactionObj = new BatchedTransactionObj();
	BatchedTransactionService batchedTransactionService;

	public ProcessBatchedTransactions(String transaction, BatchedTransactionService batchedTransactionService) {
		this.batchedTransactionService = batchedTransactionService;
		this.transactionLine = transaction;
	}
	
	public void action(){
		loadTransactionObj();
		pushTransactionToDB();
		
	}
	
	private void loadTransactionObj(){
		System.out.println(transactionLine);
		StringTokenizer st = new StringTokenizer(transactionLine,",");  
		int count = 0;
    	while (st.hasMoreTokens()) {  
    		count++;

    		if (count==1){st.nextToken();}//BACTH keyword
    		else if (count==2){batchedTransactionObj.setTranDate(st.nextToken());}//tranDate
    		else if (count==3){batchedTransactionObj.setReference(st.nextToken());}//reference
    		else if (count==4){batchedTransactionObj.setAccount(st.nextToken());}//account
    		else if (count==5){batchedTransactionObj.setCategoryTree(st.nextToken());}//categoryTree
    		else if (count==6){batchedTransactionObj.setAmount(st.nextToken());}//amount
    		else if (count==7){batchedTransactionObj.setBacth(st.nextToken());}//batch
        }
    	
    	if (batchedTransactionObj.getCategoryTree().equalsIgnoreCase("UNCATEGORISED")){
    		if (batchedTransactionObj.getAmount().startsWith("-")){
    			batchedTransactionObj.setReference("expenseUNCAT");
    		}
    		else{batchedTransactionObj.setReference("incomeUNCAT");};
    	}
    	String tranType = "I";
    	if (batchedTransactionObj.getAmount().startsWith("-")){
    		tranType = "E";
		}
    	
    	//call the get category family here
    	/*
    	//batchedTransactionObj.setCategoryTree
    	final String uri = "http://127.0.0.1:9875/fetchPath";
    	FetchPathInput fip = new FetchPathInput();
    	fip.setCategory(batchedTransactionObj.getReference());
    	fip.setTranType(tranType);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String categoryFamily= restTemplate.postForObject(uri, fip, String.class);*/
    	String categoryFamily= batchedTransactionObj.getReference();
    	batchedTransactionObj.setCategoryTree(categoryFamily);
    	System.out.println(batchedTransactionObj.toString());
    	System.out.println("result: " +categoryFamily);
	}
	
	private void pushTransactionToDB(){
		
		BatchedTransaction batchedTransaction = new BatchedTransaction(batchedTransactionObj.getBatch()+batchedTransactionObj.getTranDate()+batchedTransactionObj.getCategoryTree()+Calendar.getInstance().getTimeInMillis(),
																	   batchedTransactionObj.getTranDate(), 
																	   batchedTransactionObj.getReference(), 
																	   batchedTransactionObj.getAccount(), 
																	   batchedTransactionObj.getCategoryTree(), 
																	   new BigDecimal(batchedTransactionObj.getAmount()),
																	   batchedTransactionObj.getBatch());
		
		
		batchedTransactionService.save(batchedTransaction);
	}
}
