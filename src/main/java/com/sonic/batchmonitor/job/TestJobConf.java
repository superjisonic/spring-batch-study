package com.sonic.batchmonitor.job;

import com.sonic.batchmonitor.job.monitor.BatchMonitorJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class TestJobConf {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchMonitorJobListener batchMonitorJobListener;
    private final TestJobTasklet testJobTasklet;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep1(null))
                .listener(batchMonitorJobListener)
                .build();
    }

    private Step testStep1(@Value("#{jobParameters[currentDateTime]}") String currentDateTime) {
        log.info("testStep1 :: beginning testJobTasklet");
        return stepBuilderFactory.get("testStep1")
                .tasklet(testJobTasklet)
                .build();
    }
}
