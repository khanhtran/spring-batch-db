package com.example.batchprocessing;


import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final JobFactory jobFactory;

    @Autowired
    public JobRunner(JobLauncher jobLauncher, JobFactory jobFactory) {
        this.jobLauncher = jobLauncher;
        this.jobFactory = jobFactory;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.getSourceArgs().length == 0) {
            System.out.println("Please provide a job name as an argument!");
            return;
        }

        String jobName = args.getSourceArgs()[0];
        Job job = jobFactory.getJob(jobName);

        if (job == null) {
            System.out.println("No job found with name: " + jobName);
            return;
        }

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // Ensure uniqueness for job execution
                .toJobParameters();

        System.out.println("Starting job: " + jobName);
        jobLauncher.run(job, jobParameters);
    }
}
