package com.sonic.batchmonitor.job.monitor;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BatchMonitorReader implements ItemReader<List<BatchMonitorHistory>> {

    private final BatchMonitorHistoryService batchMonitorHistoryService;
    private final StepExecution stepExecution;

    @Override
    public List<BatchMonitorHistory> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<BatchMonitorHistory> batchMonitorHistoryList = batchMonitorHistoryService.checkBatchMonitorHistory();

        stepExecution.getJobExecution().getExecutionContext().put("batchMonitorHistoryList", batchMonitorHistoryList);

        return batchMonitorHistoryList;
    }

}
