package com.example.quartdemo.application.service;

import com.example.quartdemo.domain.BaseEntity;
import com.example.quartdemo.infrastructure.GenericRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public abstract class BaseService<E extends BaseEntity, R extends GenericRepository<E>> extends QuartJob {

    @Autowired
    protected R repository;

    public E save(E entity) {
        return this.repository.save(entity);
    }


    public E findById(Long id) {
        log.info("Find item by id: {}", id);
        return repository.findById(id).orElse(null);
    }

    public List<E> findAll() {
        log.info("Find all items");
        return repository.findAll();
    }

    public void delete(E entity) {
        log.info("Delete item: {}", entity.toString());
        this.repository.delete(entity);
    }

    public void deleteById(Long id) {
        log.info("Find item by id: {}", id);
        this.repository.deleteById(id);
    }

    @Override
    public void executeCommand(String command) {
        log.info("Execute command: {}", command);
        try {
//            String[] args = new String[]{"/bin/bash", "-c", command, "with", "args"}; // linux
            String[] args = new String[]{"cmd.exe", "/c", command}; // windows
            Process proc = new ProcessBuilder(args).start();
            log.info("RESULT:");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
