package com.patel.pradeep.chap4.reader.dbinput;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DatabaseInputApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseInputApplication.class, args);
    }
}
