package com.sonic.batchmonitor.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.YesNoType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "batch_monitor_history")
@Getter @Setter
@EqualsAndHashCode
@Builder @AllArgsConstructor @NoArgsConstructor
public class BatchMonitorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String jobName;
    private YesNoType checkedYn;
    private Long jobExecutionId;

}
