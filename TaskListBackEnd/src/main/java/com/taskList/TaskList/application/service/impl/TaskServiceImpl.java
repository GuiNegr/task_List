package com.taskList.TaskList.application.service.impl;

import com.taskList.TaskList.application.service.TaskService;

import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.model.TaskModel;
import com.taskList.TaskList.infrastrucutre.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LogServieImpl logRepository;


    @Override
    public TaskModel create(TaskDTO taskDTO) {
        TaskServiceImpl.log.info("Inicializando Cadastro da task: "+taskDTO.toString());
        TaskModel taskSave = taskRepository.save(TaskModel.toModel(taskDTO));
        LogDTO logDTO = new LogDTO("Cadastro feito com sucesso da Task: ", LocalDate.now(),LocalDate.now(),taskSave);
        logRepository.createLog(logDTO,taskSave);
        TaskServiceImpl.log.info("Cadastro Feito!");
        return taskSave;
    }

    @Override
    public void update(TaskDTO taskDTO) {
        TaskServiceImpl.log.info("Atualizando a task: "+taskDTO.toString());
    }

    @Override
    public void read(TaskDTO taskDTO) {
        TaskServiceImpl.log.info("Buscando a task: "+taskDTO.toString());
    }

    @Override
    public void delete(TaskDTO taskDTO) {
        TaskServiceImpl.log.warn("Deletando a task: "+taskDTO.toString());
    }
}
