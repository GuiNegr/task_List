package com.taskList.TaskList.application.service;

import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.model.TaskModel;

public interface LogService {

    void createLog(LogDTO logDTO, TaskModel task);
}
