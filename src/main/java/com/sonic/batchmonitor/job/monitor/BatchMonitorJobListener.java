package com.sonic.batchmonitor.job;

import com.sonic.batchmonitor.model.YesOrNo;
import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class TestJobListener extends JobExecutionListenerSupport {



    private final BatchMonitorHistoryService batchMonitorHistoryService;

    @Override
    public void beforeJob(JobExecution jobExecution){
        log.info("#### beforeJob :: insert begin");
        batchMonitorHistoryService.insertBatchMonitorHistory(jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("#### afterJob :: Job succeed! ");
            batchMonitorHistoryService.updateBatchJobStatus(jobExecution.getJobInstance().getJobName(), "Y", jobExecution.getJobInstance().getInstanceId());

        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("#### afterJob :: Job failed! ");
            batchMonitorHistoryService.updateBatchJobStatus(jobExecution.getJobInstance().getJobName(), "N", jobExecution.getJobInstance().getInstanceId());
        }
    }

}
