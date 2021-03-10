package com.sonic.batchmonitor.service;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.model.YesOrNo;
import com.sonic.batchmonitor.repository.BatchMonitorHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BatchMonitorHistoryService {

    private final BatchMonitorHistoryRepository batchMonitorHistoryRepository;

    public void updateBatchJobStatus(String jobName, YesOrNo status, long jobInstanceId){
        batchMonitorHistoryRepository.updateJobStatus(jobName, status, jobInstanceId);
    }

    public void insertBatchMonitorHistory(String jobName) {
        BatchMonitorHistory batchMonitorHistory = BatchMonitorHistory.builder()
                .jobName(jobName)
                .build();
        batchMonitorHistoryRepository.save(batchMonitorHistory);
    }
}
