package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JobFactory {

    private final Map<String, Job> jobs;

    @Autowired
    public JobFactory(ApplicationContext applicationContext) {
        // Retrieve all Job beans and store them in a map
        this.jobs = applicationContext.getBeansOfType(Job.class)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), Map.Entry::getValue));
    }

    public Job getJob(String jobName) {
        return jobs.get(jobName);
    }
}

