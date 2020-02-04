package com.gmail.ramawthar.priyash.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gmail.ramawthar.priyash.model.BatchedTransaction;

public interface BatchedTransactionRepository extends ElasticsearchRepository<BatchedTransaction, String> {

    List<BatchedTransaction> findByTranDate(String tranDate);

}
