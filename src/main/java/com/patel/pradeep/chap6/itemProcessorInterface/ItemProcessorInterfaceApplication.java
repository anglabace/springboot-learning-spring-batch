package com.patel.pradeep.chap6.itemProcessorInterface;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ItemProcessorInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemProcessorInterfaceApplication.class, args);
	}
}