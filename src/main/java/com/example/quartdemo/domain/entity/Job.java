package com.example.quartdemo.domain.entity;

import com.example.quartdemo.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job extends BaseEntity {

    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    @Column(name = "job_name", nullable = false)
    private String jobCommand;

    @Column(name = "next_time_executing")
    private LocalDateTime nextExecuting;
}
