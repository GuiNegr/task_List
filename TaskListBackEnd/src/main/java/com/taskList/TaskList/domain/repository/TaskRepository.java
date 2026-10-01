package com.taskList.TaskList.domain.repository;

import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByTaskStatus(TaskStatusEnum status);
    List<TaskModel> findByCreatedAtBetween(LocalDate start, LocalDate end);


}
