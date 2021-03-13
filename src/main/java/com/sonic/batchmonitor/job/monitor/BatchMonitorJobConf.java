package com.sonic.batchmonitor.job.monitor;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchMonitorJobConf {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchMonitorReader batchMonitorReader;
    private final RerunJobListener rerunJobListener;

    private static final int chunkSize = 10;

    @Bean
    public Job checkHistoryJob() {
        return jobBuilderFactory.get("checkHistoryJob")
                .start(getBatchHistoryStep())
                .listener(rerunJobListener)
                .build();
    }

    private Step getBatchHistoryStep() {
        return stepBuilderFactory.get("getBatchHistoryStep")
                .<BatchMonitorHistory, BatchMonitorHistory>chunk(chunkSize)
                .reader((ItemReader<? extends BatchMonitorHistory>) batchMonitorReader)
                .build();
    }

}
