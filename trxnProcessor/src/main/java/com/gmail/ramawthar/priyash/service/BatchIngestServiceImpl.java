package com.gmail.ramawthar.priyash.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.ramawthar.priyash.queueLogic.ProcessBatchedTransactions;
import com.gmail.ramawthar.priyash.queueLogic.ProcessTransactions;



@Service
public class BatchIngestServiceImpl implements BatchIngestService {

    public String processCSVFile(MultipartFile file){
    	String status = "Empty file!";
    	if (!(file.isEmpty())){
    		status = "File is being processed";
	    	BufferedReader br;
	    	List<String> result = new ArrayList<>();
	    	try {
	
	    	     String line;
	    	     InputStream is = file.getInputStream();
	    	     br = new BufferedReader(new InputStreamReader(is));
	    	     while ((line = br.readLine()) != null) {
	    	          result.add(line);
	    	          System.out.println(line);
	    	          pushToDB(line);
	    	          //pushToQueue(line);
	    	          /*TO DO: Push to elastic here*/
	    	     }
	  
	    	  } catch (IOException e) {
	    	    System.err.println(e.getMessage());       
	    	  }
	    	
	    	
	    	status = "File processed";
    	}
    	
    	return status;
    }
    
    @Autowired
    @Qualifier("trxnSrvc")
    TransactionService transactionService;
    
    @Autowired
    @Qualifier("BatchTrxnSrvc")
    BatchedTransactionService batchedTransactionService;
	private void pushToDB(String processedLine){
    	System.out.println("Received <" + processedLine + ">");
    	
        if (processedLine.startsWith("BATCH")){
        	ProcessBatchedTransactions pbt = new ProcessBatchedTransactions(processedLine.toString(), batchedTransactionService);
        	pbt.action();
        }
        else{
        ProcessTransactions pt = new ProcessTransactions(processedLine.toString(), transactionService);
        pt.action();
        }
	}
}
