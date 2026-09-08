package com.taskList.TaskList.domain.dto;

import com.taskList.TaskList.domain.model.LogModel;
import com.taskList.TaskList.domain.model.TaskModel;

import java.time.LocalDate;

public record LogDTO(String description, LocalDate createdAt, LocalDate updatedAt, TaskModel task) {

    public LogDTO toDTO(LogModel log,TaskModel task) {
        return  new LogDTO(log.getDescription(),log.getCreatedAt(),log.getUpdatedAt(),task);
    }

}

