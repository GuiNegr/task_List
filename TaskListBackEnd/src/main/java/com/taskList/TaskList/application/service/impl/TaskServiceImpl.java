package com.taskList.TaskList.application.service.impl;

import com.taskList.TaskList.application.service.TaskService;

import com.taskList.TaskList.domain.dto.LogDTO;
import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.dto.TaskResponseDTO;
import com.taskList.TaskList.domain.enums.LogTypeEnum;
import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import com.taskList.TaskList.domain.repository.TaskRepository;
import com.taskList.TaskList.shared.serviceUtils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LogServieImpl logRepository;


    @Autowired
    private TaskUtils taskUtils;

    @Override
    public TaskResponseDTO create(TaskDTO taskDTO) {
        TaskServiceImpl.log.info("Inicializando Cadastro da task: "+taskDTO.toString());
        TaskModel taskSave = taskRepository.save(TaskModel.toModel(taskDTO));
        LogDTO logDTO = new LogDTO("Cadastro feito com sucesso da Task: ", LocalDate.now(),LocalDate.now(),taskSave, LogTypeEnum.SUCCESSFUL);
        logRepository.createLog(logDTO,taskSave);
        TaskServiceImpl.log.info("Cadastro Feito!");
        return TaskModel.toResponseDTO(taskSave);
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


    @Override
    public Optional<List<TaskDTO>> findByStatus(String status) {
        TaskStatusEnum taskStatusEnum = TaskStatusEnum.getEnumByValue(status);
        if(taskStatusEnum.getEnumValue().contains("ERROR")){
            TaskServiceImpl.log.error("Erro ao buscar a task: "+status);
            return Optional.empty();
        }

        List<TaskDTO> taskList = taskRepository.findByTaskStatus(taskStatusEnum).stream().map(TaskModel::toDTO).toList();
        if(!taskList.isEmpty()){
            TaskServiceImpl.log.info("Lista de task econtrada! retornado.....");
            return Optional.of(taskList);
        }
        else{
            TaskServiceImpl.log.warn("Não foi encontrado nenhuma task com o status: "+status);
        }

        return  Optional.empty();
    }

    @Override
    public Optional<List<TaskDTO>> findByCreatedAtBetween(LocalDate start, LocalDate end) {
        List<TaskModel> taskModels = taskRepository.findByCreatedAtBetween(start, end);
        List<TaskDTO> taskDTOS = taskUtils.transformTaskModelsToDTOs(taskModels);
        if(taskDTOS.isEmpty()){
            TaskServiceImpl.log.warn("Lista de task não encontrada!");
            return Optional.empty();
        }

        return Optional.of(taskDTOS);
    }

    @Override
    public Optional<List<TaskDTO>> returnAllTask() {
        TaskServiceImpl.log.info("Retornando todas astasks!");
        List<TaskDTO> tasks = taskRepository.findAll().stream().map(TaskModel::toDTO).toList();
        return Optional.of(tasks);
    }
}
