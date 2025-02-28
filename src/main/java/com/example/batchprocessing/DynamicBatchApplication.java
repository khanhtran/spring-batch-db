package com.example.batchprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicBatchApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(DynamicBatchApplication.class, args)));
	}


}
