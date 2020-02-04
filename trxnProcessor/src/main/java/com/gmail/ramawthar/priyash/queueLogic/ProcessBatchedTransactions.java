package com.gmail.ramawthar.priyash.queueLogic;

import java.math.BigDecimal;
import java.util.Calendar;

import com.gmail.ramawthar.priyash.model.BatchedTransaction;
import com.gmail.ramawthar.priyash.model.Transaction;
import com.gmail.ramawthar.priyash.service.BatchedTransactionService;

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
		//pushTransactionToDB();
		
	}
	
	private void loadTransactionObj(){
		System.out.println(transactionLine);
	}
	
	private void pushTransactionToDB(){
		
		BatchedTransaction batchedTransaction = new BatchedTransaction(batchedTransactionObj.getTranDate()+batchedTransactionObj.getCategoryTree()+Calendar.getInstance().getTimeInMillis(),
																	   batchedTransactionObj.getTranDate(), 
																	   batchedTransactionObj.getReference(), 
																	   batchedTransactionObj.getAccount(), 
																	   batchedTransactionObj.getCategoryTree(), 
																	   new BigDecimal(batchedTransactionObj.getAmount().toString()),
																	   batchedTransactionObj.getBacth());
		
		
		batchedTransactionService.save(batchedTransaction);
	}
}
