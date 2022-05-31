package com.example.quartdemo.application.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddJobRequest {

    @JsonProperty("cron_expression")
    private String cronExpression;

    @JsonProperty("job_command")
    private String jobCommand;


}
