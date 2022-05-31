package com.example.quartdemo.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Trigger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SchedulerInfo {

    private JobDetail jobDetail;
    private Trigger trigger;

}
