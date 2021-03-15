package com.sonic.batchmonitor.job.monitor;

import com.sonic.batchmonitor.BatchMonitorApplication;
import com.sonic.batchmonitor.BatchTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={BatchMonitorApplication.class, BatchTestConfig.class})
@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class BatchMonitorJobConfTest {


    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobLauncher jobLauncher;


    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private BatchMonitorJobConf batchMonitorJobConf;

    @Before
    public void setUp() {
        this.jobLauncherTestUtils = new JobLauncherTestUtils();
        this.jobLauncherTestUtils.setJobLauncher(jobLauncher);
        this.jobLauncherTestUtils.setJobRepository(jobRepository);
        this.jobLauncherTestUtils.setJob(batchMonitorJobConf.checkHistoryJob());
    }


    @Test
    public void testJob() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();


        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}