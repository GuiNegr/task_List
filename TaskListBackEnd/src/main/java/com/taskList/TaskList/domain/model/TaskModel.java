package com.taskList.TaskList.domain.model;


import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@ToString
@Table(name = "Task")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "taskstatus")
    private TaskStatusEnum taskStatus;

    public  TaskModel(String title, String description, LocalDate date, LocalDate date1,TaskStatusEnum taskStatus) {
        this.title = title;
        this.description = description;
        this.createdAt = date;
        this.updatedAt = date1;
        this.taskStatus = taskStatus;
    }


    public static TaskModel toModel(TaskDTO taskDTO){
        return new TaskModel(taskDTO.title(),taskDTO.description(),taskDTO.createdAt(),taskDTO.updatedAt(),taskDTO.taskStatusEnum());
    }

}
