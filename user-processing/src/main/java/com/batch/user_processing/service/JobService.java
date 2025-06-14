package com.batch.user_processing.service;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobService {

    @Autowired
    private JobScheduler jobScheduler;

    @Job(name = "Test-JOB", retries = 2)
    @Recurring(id = "TEST-JOB", cron = "*/5 * * * *")
    public void runCronJobTest(){
        log.info("Start recurring job in JobService");
    }
}
