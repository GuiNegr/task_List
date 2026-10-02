package com.taskList.TaskList.application.service;


import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.dto.TaskResponseDTO;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskService {

    TaskResponseDTO create(TaskDTO TaskDTO);
    Optional<TaskResponseDTO> update(Map<String, Object> request);
    void read(TaskDTO TaskDTO);
    void deleteById(Long id);
    Optional<List<TaskDTO>> findByStatus(String status);
    Optional<List<TaskDTO>> findByCreatedAtBetween(LocalDate start, LocalDate end);
    Optional<List<TaskDTO>> returnAllTask();

}
