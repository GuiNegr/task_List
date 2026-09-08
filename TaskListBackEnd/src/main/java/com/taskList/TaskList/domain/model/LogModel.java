package com.taskList.TaskList.domain.model;


import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.enums.LogTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Table(name = "Log")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    //TODO: deixar o log associado a uma task
    @ManyToOne
    @JoinColumn(name = "id_task")
    private TaskModel task;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "log_type")
    private LogTypeEnum logType;


    //Dto Construct
    public LogModel(String description, LocalDate date, LocalDate date1,LogTypeEnum logType) {
        this.description = description;
        this.createdAt = date;
        this.updatedAt = date1;
        this.logType = logType;
    }


    public static LogModel toModel (LogDTO log) {
        return new LogModel(log.description(),log.createdAt(),log.updatedAt(),log.logTypeEnum());
    }

}
