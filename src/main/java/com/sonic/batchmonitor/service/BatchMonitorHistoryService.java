package com.sonic.batchmonitor.service;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.model.YesOrNo;
import com.sonic.batchmonitor.repository.BatchMonitorHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BatchMonitorHistoryService {

    private final BatchMonitorHistoryRepository batchMonitorHistoryRepository;

    public void updateBatchJobStatus(String jobName, String status, long jobInstanceId){
        log.info("updating...... jobName = {} status = {} jobInstanceId = {}",jobName, status, jobInstanceId);
        batchMonitorHistoryRepository.updateJobStatus(jobName, status, jobInstanceId);
    }

    public void insertBatchMonitorHistory(String jobName) {
        BatchMonitorHistory batchMonitorHistory = BatchMonitorHistory.builder()
                .jobName(jobName)
                .jobInstanceId(null)
                .build();
        batchMonitorHistoryRepository.save(batchMonitorHistory);
    }
}
