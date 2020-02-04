package com.gmail.ramawthar.priyash.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.ramawthar.priyash.queueLogic.ProcessBatchedTransactions;
import com.gmail.ramawthar.priyash.queueLogic.ProcessTransactions;
import com.gmail.ramawthar.priyash.service.BatchedTransactionService;
import com.gmail.ramawthar.priyash.service.TransactionService;

@Component
public class Receiver {

    @Autowired
    TransactionService transactionService;
    
    @Autowired
    BatchedTransactionService batchedTransactionService;
    
    public void receiveMessage(Object message) {
        System.out.println("Received <" + message.toString() + ">");
        
        if (message.toString().startsWith("BATCH")){
        	ProcessBatchedTransactions pbt = new ProcessBatchedTransactions(message.toString(), batchedTransactionService);
        	pbt.action();
        }
        else{
        ProcessTransactions pt = new ProcessTransactions(message.toString(), transactionService);
        pt.action();
        }
        
    }

}
