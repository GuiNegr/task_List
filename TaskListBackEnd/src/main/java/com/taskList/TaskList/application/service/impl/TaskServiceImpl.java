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
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LogServieImpl logRepository;



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
    public Optional<TaskResponseDTO> update(Map<String, Object> request) {
        Long id = (Long) request.get("id");

        if(id==null){
            TaskServiceImpl.log.error("Não foi informado o id para atualização da task");
            return Optional.empty();
        }

        TaskModel oldTask =  taskRepository.findById(id).orElse(null);

        if(oldTask==null){
            TaskServiceImpl.log.error("Id informado não trouxe task");
            return  Optional.empty();
        }

        TaskResponseDTO taskResponseDTO = TaskModel.toResponseDTO(oldTask);


        if(request.get("description") != null  ){
            taskResponseDTO = updateTaskDescription((String) request.get("description"), id);
        }

        if(request.get("title") != null  ){
            taskResponseDTO = updateTaskTitle((String) request.get("title"), id);
        }

        if(request.get("status") != null  ){
            TaskStatusEnum taskStatusEnum = TaskStatusEnum.getEnumByValue((String) request.get("status"));
            if(taskStatusEnum.getEnumValue().contains("ERROR")){
                TaskServiceImpl.log.error("erro ao atualizar status da task: ");
                return Optional.empty();
            }
            taskResponseDTO = updateTaskStatus(taskStatusEnum, id);
        }
        return Optional.of(taskResponseDTO);
    }

    @Override
    public void read(TaskDTO taskDTO) {
        TaskServiceImpl.log.info("Buscando a task: "+taskDTO.toString());
    }

    @Override
    public void deleteById(Long id) {
        TaskServiceImpl.log.info("Buscando a task: "+id);
        TaskModel task = taskRepository.findById(id).orElse(null);
        if(task==null){
            TaskServiceImpl.log.error("Não foi possivel encontrar a task com o id informado");

        }else{
            taskRepository.delete(task);
            TaskServiceImpl.log.info("Task deletado com sucesso!");
        }
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
        List<TaskDTO> taskDTOS = TaskUtils.transformTaskModelsToDTOs(taskModels);
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

    private @NonNull TaskResponseDTO updateTaskDescription(String description, Long id) {
        TaskModel oldTask = taskRepository.findById(id).orElse(null);
        oldTask.setDescription(description);
        oldTask.setUpdatedAt(LocalDate.now());
        oldTask = taskRepository.save(oldTask);
        TaskServiceImpl.log.info("Task Atualizada: ");
        LogDTO logDTO = new LogDTO("Task Atualizada com sucesso!",LocalDate.now(),LocalDate.now(),oldTask, LogTypeEnum.SUCCESSFUL);
        logRepository.createLog(logDTO,oldTask);
        return TaskModel.toResponseDTO(oldTask);
    }

    private TaskResponseDTO updateTaskTitle(String title,Long id) {
        TaskModel oldTask = taskRepository.findById(id).orElse(null);
        oldTask.setTitle(title);
        oldTask.setUpdatedAt(LocalDate.now());
        oldTask = taskRepository.save(oldTask);
        TaskServiceImpl.log.info("Task Atualizada: ");
        LogDTO logDTO = new LogDTO("Task Atualizada com sucesso!",LocalDate.now(),LocalDate.now(),oldTask, LogTypeEnum.SUCCESSFUL);
        logRepository.createLog(logDTO,oldTask);
        return TaskModel.toResponseDTO(oldTask);
    }

    private TaskResponseDTO updateTaskStatus(TaskStatusEnum taskStatusEnum,Long id) {
        TaskModel oldTask = taskRepository.findById(id).orElse(null);
        oldTask.setTaskStatus(taskStatusEnum);
        oldTask.setUpdatedAt(LocalDate.now());
        oldTask = taskRepository.save(oldTask);
        TaskServiceImpl.log.info("Task Atualizada: ");
        LogDTO logDTO = new LogDTO("Task Atualizada com sucesso!",LocalDate.now(),LocalDate.now(),oldTask, LogTypeEnum.SUCCESSFUL);
        logRepository.createLog(logDTO,oldTask);
        return TaskModel.toResponseDTO(oldTask);
    }
}
