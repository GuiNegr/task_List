package com.taskList.TaskList.application.service;

import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.model.TaskModel;

import java.util.List;

public interface LogService {

    void createLog(LogDTO logDTO, TaskModel task);
    List<LogDTO> getLogs(TaskModel task);
}
