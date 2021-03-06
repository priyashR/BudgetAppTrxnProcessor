package com.gmail.ramawthar.priyash.trxnProcessor;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

import com.gmail.ramawthar.priyash.service.BatchedTransactionService;
import com.gmail.ramawthar.priyash.service.BatchedTransactionServiceImpl;
import com.gmail.ramawthar.priyash.service.TransactionService;
import com.gmail.ramawthar.priyash.service.TransactionServiceImpl;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.gmail.ramawthar.priyash.repository")
public class EsConfig {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder().put("cluster.name", EsClusterName)
                .build();
    	
    	

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        //return TransportClient.builder()
        //        .settings(esSettings)
        //        .build()
        //        .addTransportAddress(
		//		  new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
        
        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));
        
        return client;
        
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

    //Embedded Elasticsearch Server
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/
    
    @Bean
    public TransactionService TransactionServiceBean() {
        return new TransactionServiceImpl();
    }
    
    @Bean
    public BatchedTransactionService BatchedTransactionServiceBean() {
        return new BatchedTransactionServiceImpl();
    }
    
}

