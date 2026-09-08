package com.taskList.TaskList.application.service.impl;

import com.taskList.TaskList.application.service.LogService;
import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.model.LogModel;
import com.taskList.TaskList.infrastrucutre.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogServieImpl implements LogService {

    @Autowired
   private LogRepository logRepository;


    @Override
    public void createLog(LogDTO logDTO) {
        LogModel log = LogModel.toModel(logDTO);
        logRepository.save(log);
    }
}
