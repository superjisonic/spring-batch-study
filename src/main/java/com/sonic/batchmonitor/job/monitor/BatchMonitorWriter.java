package com.sonic.batchmonitor.job.monitor;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BatchMonitorWriter implements ItemWriter<BatchMonitorHistory> {
    private final BatchMonitorHistoryService batchMonitorHistoryService;


    @Override
    public void write(List<? extends BatchMonitorHistory> items) throws Exception {
        items.stream().forEach(
                item -> batchMonitorHistoryService.rerunFailedBatch(item)
        );
    }
}
