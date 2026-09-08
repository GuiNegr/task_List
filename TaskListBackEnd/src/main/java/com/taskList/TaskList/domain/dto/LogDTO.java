package com.taskList.TaskList.domain.dto;

import com.taskList.TaskList.domain.model.LogModel;

import java.time.LocalDate;

public record LogDTO(String description, LocalDate createdAt, LocalDate updatedAt) {

    public LogDTO toDTO(LogModel log) {
        return  new LogDTO(log.getDescription(),log.getCreatedAt(),log.getUpdatedAt());
    }

}

