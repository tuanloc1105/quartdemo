package com.example.quartdemo.integration;

import com.example.quartdemo.application.message.AddJobRequest;
import com.example.quartdemo.application.service.JobService;
import com.example.quartdemo.domain.entity.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job/")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final JobService jobService;

    @PostMapping("/create")
    public Job createJob(@RequestBody AddJobRequest request) throws SchedulerException {
        Job job = new Job(request.getCronExpression(), request.getJobCommand(), null);
        return this.jobService.saveJob(job);
    }

    @DeleteMapping("/delete/{id}")
    public void removeJob(@PathVariable(name = "id") Long id) {
        this.jobService.deleteJob(id);
    }

}
