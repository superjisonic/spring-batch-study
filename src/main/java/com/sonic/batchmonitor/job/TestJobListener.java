package com.sonic.batchmonitor.job;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TestJobListener extends JobExecutionListenerSupport {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TestJobListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestJobListener(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("#### chunkJob successed! ");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("#### chunkJob failed! ");
        }
    }

}
