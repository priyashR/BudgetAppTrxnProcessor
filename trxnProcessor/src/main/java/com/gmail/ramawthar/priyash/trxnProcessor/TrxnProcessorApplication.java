package com.gmail.ramawthar.priyash.trxnProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@ComponentScan({"com.gmail.ramawthar.priyash.rabbit"}) - not going to use the queues
@ComponentScan({"com.gmail.ramawthar.priyash.trxnProcessor"})
@ComponentScan({"com.gmail.ramawthar.priyash.controller"})
@ComponentScan({"com.gmail.ramawthar.priyash.service"})
public class TrxnProcessorApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(TrxnProcessorApplication.class, args);
	}
}
