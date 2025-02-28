package com.example.batchprocessing;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Job1Configuration {

	@Bean
	public Step step1(JobRepository jobRepository, SampleTask sampleTask, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("step1", jobRepository).tasklet(sampleTask, platformTransactionManager).build();
	}

	@Bean
	public Job job1(JobRepository jobRepository, Step step1) {
		return new JobBuilder("job1", jobRepository)
			.start(step1)
			.build();
	}

}
