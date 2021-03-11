package com.sonic.batchmonitor.repository;

import com.sonic.batchmonitor.model.BatchMonitorHistory;
import com.sonic.batchmonitor.model.YesOrNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BatchMonitorHistoryRepository extends JpaRepository<BatchMonitorHistory, Long>, QuerydslPredicateExecutor<BatchMonitorHistory> {

    @Transactional
    @Modifying
    @Query(value ="update batch_monitor_history set checked_yn= ?2, job_instance_id = ?3" +
            " where checked_yn is null and job_instance_id is null and job_name = ?1 and create_ymdt <= now()"
            , nativeQuery=true)
    int updateJobStatus(String jobName, String status, long jobInstanceId);


}
