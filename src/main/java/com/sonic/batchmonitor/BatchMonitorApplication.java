package com.sonic.batchmonitor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("mysql")
@EnableBatchProcessing
@SpringBootApplication
public class BatchMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchMonitorApplication.class, args);
    }

}
