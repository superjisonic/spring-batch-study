package com.sonic.batchmonitor.job;

import com.sonic.batchmonitor.model.YesOrNo;
import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@RequiredArgsConstructor
@Component
public class TestJobListener extends JobExecutionListenerSupport {

    private static final Logger log = (Logger) LoggerFactory.getLogger(TestJobListener.class);

    private final BatchMonitorHistoryService batchMonitorHistoryService;

    @Override
    public void beforeJob(JobExecution jobExecution){
        batchMonitorHistoryService.insertBatchMonitorHistory(jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("#### chunkJob successed! ");
            batchMonitorHistoryService.updateBatchJobStatus(jobExecution.getJobInstance().getJobName(), YesOrNo.Y, jobExecution.getJobInstance().getInstanceId());

        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("#### chunkJob failed! ");
            batchMonitorHistoryService.updateBatchJobStatus(jobExecution.getJobInstance().getJobName(), YesOrNo.N, jobExecution.getJobInstance().getInstanceId());
        }
    }

}
