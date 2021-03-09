package com.sonic.batchmonitor.model;

import lombok.Data;
import org.hibernate.type.YesNoType;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class BatchMonitorHistory {
//     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
//  `batch_job_name` varchar(200) NOT NULL DEFAULT '',
//            `job_start_time` timestamp NULL DEFAULT NULL,
//  `job_end_time` timestamp NULL DEFAULT NULL,
//  `checked_yn` char(1) DEFAULT NULL,
//  `create_ymdt` timestamp NULL DEFAULT NULL,
//  `update_ymdt` timestamp NULL DEFAULT NULL,

    @Id
    private long id;
    private String batchJobName;
    private LocalDateTime jobStartTime;
    private LocalDateTime jobEndTime;
    private YesNoType checkedYn;
    private LocalDateTime createYmdt;
    private LocalDateTime updateYmdt;

}
