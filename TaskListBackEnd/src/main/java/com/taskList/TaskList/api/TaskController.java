package com.taskList.TaskList.api;

import com.taskList.TaskList.application.service.TaskService;
import com.taskList.TaskList.domain.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam(required = false) String status){
        if (status != null){
            Optional<List<TaskDTO>> tasks = taskService.findByStatus(status);
            return tasks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        Optional<List<TaskDTO>> tasks = taskService.returnAllTask();
        return tasks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
