package com.example.quartdemo.application.service;

import com.example.quartdemo.domain.entity.Job;
import com.example.quartdemo.infrastructure.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class JobService extends BaseService<Job, JobRepository> {

    private final SchedulerService schedulerService;

    public Job saveJob(Job job) throws SchedulerException {
        log.info("SAVE NEW JOB");
        CronExpression cronExpression = CronExpression.parse(job.getCronExpression());
        LocalDateTime nextTimeRun = cronExpression.next(LocalDateTime.now());
        job.setNextExecuting(nextTimeRun);
        Job jobSaved = this.save(job);
        this.schedulerService.startJob(jobSaved);
        return jobSaved;
    }

    public List<Job> findJobByNextExecutingTime(LocalDateTime time) {
        log.info("FIND ALL JOB AT TIME: {}", time);
        return this.repository.findByNextExecuting(time);
    }

    public void deleteJob(Long id) {
        log.info("DELETE JOB BY ID: {}", id);
        this.schedulerService.stopJob(this.findById(id));
        this.deleteById(id);
    }
}
