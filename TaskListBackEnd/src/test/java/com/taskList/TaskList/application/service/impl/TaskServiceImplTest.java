package com.taskList.TaskList.application.service.impl;

import com.taskList.TaskList.application.service.TaskService;
import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.dto.TaskResponseDTO;
import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import com.taskList.TaskList.domain.repository.LogRepository;
import com.taskList.TaskList.domain.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class TaskServiceImplTest {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveInPostgreeSQL() {

        TaskDTO task = new TaskDTO(
                "Teste Junit",
                "Teste feito com o junit",
                LocalDate.now(),
                LocalDate.now(),
                TaskStatusEnum.NEW
        );

        TaskResponseDTO taskCreated = taskService.create(task);

        Assertions.assertNotNull(taskCreated);

        Assertions.assertNotNull(taskCreated.id());

        Optional<TaskModel> taskFromDatabase =
                taskRepository.findById(taskCreated.id());

        Assertions.assertTrue(taskFromDatabase.isPresent());

        Assertions.assertEquals(
                "Teste Junit",
                taskFromDatabase.get().getTitle()
        );

        Assertions.assertEquals(
                "Teste feito com o junit",
                taskFromDatabase.get().getDescription()
        );
    }

    @Test
    void shouldReturnAListWithTaskStatusEqualsNew(){
        Optional<List<TaskDTO>> taskList = taskService.findByStatus("NEW");
        Assertions.assertTrue(taskList.isPresent());
    }

    @Test
    void shouldNOtReturnAListIfDoesNotExistTaskWithStatusThatHasPassed(){
        Optional<List<TaskDTO>> taskList = taskService.findByStatus("QUALQUER_NOME");
        Assertions.assertFalse(taskList.isPresent());
    }

    @Test
    void shouldReturnAlistWithAllTasks(){
        Optional<List<TaskDTO>> taskList = taskService.returnAllTask();
        Assertions.assertTrue(taskList.isPresent());
    }

    @Test
    void shouldReturnAListWithTasksInCurrentDate(){
        Optional<List<TaskDTO>> taskList = taskService.findByCreatedAtBetween(LocalDate.now().minusDays(12), LocalDate.now().plusDays(1));
        Assertions.assertTrue(taskList.isPresent());
    }

    @Test
    void shouldNotReturnAListWithTasksInCurrentDate(){
        Optional<List<TaskDTO>> taskList = taskService.findByCreatedAtBetween(LocalDate.now().plusDays(20), LocalDate.now().plusDays(30));
        Assertions.assertTrue(taskList.isEmpty());
    }

    @Test
    void shouldUpateTitleWithSucess(){
        TaskDTO task = new TaskDTO(
                "Teste Junit",
                "Teste feito com o junit",
                LocalDate.now(),
                LocalDate.now(),
                TaskStatusEnum.NEW
        );

        TaskResponseDTO taskCreated = taskService.create(task);
        Map<String, Object> request = new HashMap<>();
        request.put("title","Task atualizada pelo JuNit");
        request.put("id",taskCreated.id());
        Optional<TaskResponseDTO> taskTest = taskService.update(request);

        Assertions.assertTrue(taskTest.get().title().equals("Task atualizada pelo JuNit"));
    }

    @Test
    void sholdNotUpdateTitleIfNotExistId(){
        Random random = new Random(System.currentTimeMillis());

        Map<String, Object> request = new HashMap<>();
        request.put("title","Task atualizada pelo JuNit");
        request.put("id",random.nextLong());
        Optional<TaskResponseDTO> taskTest = taskService.update(request);

        Assertions.assertTrue(taskTest.isEmpty(), "Deve estar vazio taskResponse!");
    }


}
