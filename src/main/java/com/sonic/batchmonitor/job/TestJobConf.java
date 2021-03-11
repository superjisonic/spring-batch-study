package com.sonic.batchmonitor.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class TestJobConf {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TestJobListener testJobListener;
    private final TestJobTasklet testJobTasklet;

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("testJob")
                .start(testStep1())
                .listener(testJobListener)
                .build();
    }

    private Step testStep1() {
        log.info("testStep1 :: beginning testJobTasklet");
        return stepBuilderFactory.get("testStep1")
                .tasklet(testJobTasklet)
                .build();
    }
}
