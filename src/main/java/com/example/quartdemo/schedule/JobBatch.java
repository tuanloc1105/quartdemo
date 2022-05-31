package com.example.quartdemo.schedule;

import com.example.quartdemo.application.service.JobService;
import com.example.quartdemo.domain.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

@Slf4j
public class JobBatch extends BaseBatch<JobService> {
    @Override
    protected void execute(Job job) {
        log.info("START EXECUTING");
        this.service.executeCommand(job.getJobCommand());
        CronExpression cronExpression = CronExpression.parse(job.getCronExpression());
        LocalDateTime nextTimeRun = cronExpression.next(LocalDateTime.now());
        job.setNextExecuting(nextTimeRun);
        this.service.save(job);
    }
}
