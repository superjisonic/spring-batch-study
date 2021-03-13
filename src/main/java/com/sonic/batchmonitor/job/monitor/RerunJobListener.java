package com.sonic.batchmonitor.job.monitor;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RerunJobListener implements JobExecutionListener {
    private final JobOperator jobOperator;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        List<BatchMonitorHistory> batchMonitorHistoryList = (List<BatchMonitorHistory>) executionContext.get("batchMonitorHistoryList");
        batchMonitorHistoryList.stream().forEach(
                item ->  rerunFailedJob(item.getJobExecutionId())
                );
    }


    public void rerunFailedJob(Long jobExecutionId)  {
        try {
            jobOperator.restart(jobExecutionId);
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (NoSuchJobExecutionException e) {
            e.printStackTrace();
        } catch (NoSuchJobException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
