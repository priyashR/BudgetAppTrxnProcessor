package com.gmail.ramawthar.priyash.service;

import java.util.List;

import com.gmail.ramawthar.priyash.model.BatchedTransaction;

public interface BatchedTransactionService {

	BatchedTransaction save (BatchedTransaction batchedTransaction);

    void delete(BatchedTransaction batchedTransaction);
    
    List<BatchedTransaction> findByTranDate(String tranDate);
}
