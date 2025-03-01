package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Job2Configuration {

	@Bean
	public Step job2Step1(JobRepository jobRepository, SampleTask sampleTask, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("job2Step1", jobRepository).tasklet(sampleTask, platformTransactionManager).build();
	}

	@Bean
	public Job job2(JobRepository jobRepository, Step step1) {
		return new JobBuilder("job2", jobRepository)
			.start(step1)
			.build();
	}


}
