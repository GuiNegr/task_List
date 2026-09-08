package com.taskList.TaskList.domain.model;


import com.taskList.TaskList.domain.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public  TaskModel(String title, String description, LocalDate date, LocalDate date1) {
        this.title = title;
        this.description = description;
        this.createdAt = date;
        this.updatedAt = date1;
    }


    public static TaskModel toModel(TaskDTO taskDTO){
        return new TaskModel(taskDTO.title(),taskDTO.description(),taskDTO.createdAt(),taskDTO.updatedAt());
    }

}
