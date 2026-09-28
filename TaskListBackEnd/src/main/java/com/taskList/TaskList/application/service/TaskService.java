package com.taskList.TaskList.application.service;


import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.dto.TaskResponseDTO;
import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskResponseDTO create(TaskDTO TaskDTO);
    void update(TaskDTO TaskDTO);
    void read(TaskDTO TaskDTO);
    void delete(TaskDTO TaskDTO);
    Optional<List<TaskDTO>> findByStatus(String status);
    Optional<List<TaskDTO>> returnAllTask();

}
