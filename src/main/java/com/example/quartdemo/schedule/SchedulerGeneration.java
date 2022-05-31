package com.example.quartdemo.schedule;

import com.example.quartdemo.application.service.SchedulerService;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class SchedulerGeneration {

    public static SchedulerInfo createSchedulerInfo(Class<? extends Job> jobClass,
                                                    com.example.quartdemo.domain.entity.Job job,
                                                    SchedulerService schedulerService) {
        JobKey jobKey;
        JobDetail jobDetail;
        Trigger trigger;
        jobKey = new JobKey(String.valueOf(job.getId()), "Quart demo");
        JobDataMap map = new JobDataMap();
        map.put("jobInfo", job);
        map.put("jobServiceImpl", schedulerService);
        jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobKey.getName())
                .setJobData(map)
                .build();

        trigger = TriggerBuilder.newTrigger()
                .withIdentity(TriggerKey.triggerKey(jobKey.getName()))
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                .build();

        SchedulerInfo jobScheduler = new SchedulerInfo();
        jobScheduler.setJobDetail(jobDetail);
        jobScheduler.setTrigger(trigger);

        return jobScheduler;
    }

}
