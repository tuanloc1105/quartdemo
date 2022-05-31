package com.example.quartdemo.infrastructure.repository;

import com.example.quartdemo.domain.entity.Job;
import com.example.quartdemo.infrastructure.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends GenericRepository<Job> {

    List<Job> findByNextExecuting(LocalDateTime time);

    Boolean existsByJobCommand(String command);

}
