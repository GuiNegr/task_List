package com.taskList.TaskList.domain.dto;

import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;

import java.time.LocalDate;

public record TaskResponseDTO(Long id,String title, String description, LocalDate createdAt, LocalDate updatedAt, TaskStatusEnum taskStatusEnum)  {

    public TaskResponseDTO toDTO(TaskModel taskModel,TaskStatusEnum task) {
        return new TaskResponseDTO(taskModel.getId(),taskModel.getTitle(),taskModel.getDescription(),taskModel.getCreatedAt(),taskModel.getUpdatedAt(), task);
    }

}
