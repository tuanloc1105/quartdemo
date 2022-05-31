package com.example.quartdemo.application.service;

import com.example.quartdemo.domain.entity.Job;
import com.example.quartdemo.schedule.JobBatch;
import com.example.quartdemo.schedule.SchedulerGeneration;
import com.example.quartdemo.schedule.SchedulerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final Scheduler scheduler;

    private final HashMap<Long, SchedulerInfo> schedulerInfos;

    public void startJob(Job jobInfo) throws SchedulerException {
        try {
            SchedulerInfo schedulerInfo = SchedulerGeneration.createSchedulerInfo(JobBatch.class, jobInfo, this);
            this.scheduler.scheduleJob(schedulerInfo.getJobDetail(), schedulerInfo.getTrigger());

            if (!scheduler.isStarted()) {
                scheduler.start();
            }
            schedulerInfos.put(jobInfo.getId(), schedulerInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void stopJob(Job jobInfo) {
        if (Objects.isNull(scheduler)) {
            return;
        }
        SchedulerInfo schedulerInfo = schedulerInfos.get(jobInfo.getId());
        try {
            scheduler.deleteJob(schedulerInfo.getJobDetail().getKey());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
