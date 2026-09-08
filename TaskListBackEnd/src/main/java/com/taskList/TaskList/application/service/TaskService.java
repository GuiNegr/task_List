package com.taskList.TaskList.application.service;


import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.model.TaskModel;

public interface TaskService {

    TaskModel create(TaskDTO TaskDTO);
    void update(TaskDTO TaskDTO);
    void read(TaskDTO TaskDTO);
    void delete(TaskDTO TaskDTO);

}
