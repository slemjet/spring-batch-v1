package com.example.springbatchv1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("batch/load/stocks/")
public class BatchController {

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importStocksJPAJob")
    private Job importStocksJPAJob;

    @Autowired
    @Qualifier("importStocksMongoJob")
    private Job importStocksMongoJob;

    @PostMapping(path = "/jpa")
    @Operation(description = "Job to load stocks using JPA")
    public void loadStocksJpaBatchJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .addString("test", "value")
                .toJobParameters();
        try {
            jobLauncher.run(importStocksJPAJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {

            log.error(e.getMessage());
        }
    }

    @PostMapping(path = "/mongo")
    @Operation(description = "Job to load stocks using mongo")
    public void loadStocksMongoBatchJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(importStocksMongoJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {

            log.error(e.getMessage());
        }
    }
}
