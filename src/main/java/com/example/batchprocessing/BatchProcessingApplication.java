package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;

@SpringBootApplication
public class BatchProcessingApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(BatchProcessingApplication.class, args)));
	}

	@Bean
	public CommandLineRunner runJob(JobLauncher jobLauncher, Job job, Environment environment) {
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLocalDateTime("startAt", LocalDateTime.parse(environment.getProperty("START_AT")))
					.addLong("runId", System.currentTimeMillis()) // this allows unique run id, so job can restart
					.toJobParameters();

			JobExecution execution = jobLauncher.run(job, jobParameters);
			System.out.println("Job Exit Status: " + execution.getStatus());
		};
	}
}
