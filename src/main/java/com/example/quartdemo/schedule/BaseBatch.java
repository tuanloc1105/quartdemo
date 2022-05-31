package com.example.quartdemo.schedule;

import com.example.quartdemo.application.service.QuartJob;
import com.example.quartdemo.domain.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public abstract class BaseBatch<S extends QuartJob> extends QuartzJobBean {

    @Autowired
    protected S service;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Start executing the job" + jobExecutionContext.getJobInstance().toString());
        LocalDateTime timeExecute = jobExecutionContext
                .getScheduledFireTime()
                .toInstant()
                .atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .toLocalDateTime();
        Job job = (Job) jobExecutionContext.getMergedJobDataMap().get("jobInfo");
        this.execute(job);
        log.info("End executing the job" + jobExecutionContext.getJobInstance().toString());
    }

    protected abstract void execute(Job job);
}
