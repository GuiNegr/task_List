package com.taskList.TaskList.application.service.impl;

import com.taskList.TaskList.application.service.TaskService;
import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import com.taskList.TaskList.infrastrucutre.repository.LogRepository;
import com.taskList.TaskList.infrastrucutre.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class TaskServiceImplTest {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LogRepository logRepository;




    @Test
    void shouldSaveInPostgreeSQL() {

        TaskDTO task = new TaskDTO(
                "Teste Junit",
                "Teste feito com o junit",
                LocalDate.now(),
                LocalDate.now(),
                TaskStatusEnum.NEW
        );

        TaskModel taskCreated = taskService.create(task);

        Assertions.assertNotNull(taskCreated);

        Assertions.assertNotNull(taskCreated.getId());

        Optional<TaskModel> taskFromDatabase =
                taskRepository.findById(taskCreated.getId());

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

}
