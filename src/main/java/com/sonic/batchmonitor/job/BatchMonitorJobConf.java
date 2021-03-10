package com.sonic.batchmonitor.job;

import com.sonic.batchmonitor.service.BatchMonitorHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchMonitorJobConf {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchMonitorHistoryService batchMonitorHistoryService;

    @Bean
    public Job checkHistoryJob() {
        return jobBuilderFactory.get("checkHistoryJob")
                .start(getBatchHistoryStep())
                .build();
    }

    private Step getBatchHistoryStep() {
        return stepBuilderFactory.get("getBatchHistoryStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> Get Batch History From BATCH_JOB_EXECUTION");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
