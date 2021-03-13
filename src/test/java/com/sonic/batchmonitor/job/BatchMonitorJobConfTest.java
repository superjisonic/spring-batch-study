package com.sonic.batchmonitor;

import com.sonic.batchmonitor.job.BatchMonitorJobConf;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@RunWith(SpringRunner.class)
@RequiredArgsConstructor
@ContextConfiguration(classes = BatchMonitorJobConf.class)
public class BatchMonitorJobConfTest {

    private final JobLauncherTestUtils jobLauncherTestUtils;

    public void testBatchMonitorHistoryJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getStatus().equals(BatchStatus.COMPLETED));

    }


}
