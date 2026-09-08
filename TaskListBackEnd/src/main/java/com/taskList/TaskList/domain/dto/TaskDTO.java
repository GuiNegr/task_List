package com.taskList.TaskList.domain.dto;

import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;

import java.time.LocalDate;

public record TaskDTO(String title, String description, LocalDate createdAt, LocalDate updatedAt, TaskStatusEnum taskStatusEnum) {

    public TaskDTO toDTO(TaskModel taskModel,TaskStatusEnum task) {
        return new TaskDTO(taskModel.getTitle(),taskModel.getDescription(),taskModel.getCreatedAt(),taskModel.getUpdatedAt(), task);
    }

}
