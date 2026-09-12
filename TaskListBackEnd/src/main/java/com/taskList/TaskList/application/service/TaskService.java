package com.taskList.TaskList.application.service;


import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.model.TaskModel;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskModel create(TaskDTO TaskDTO);
    void update(TaskDTO TaskDTO);
    void read(TaskDTO TaskDTO);
    void delete(TaskDTO TaskDTO);
    Optional<List<TaskModel>> findByStatus(String status);

}
