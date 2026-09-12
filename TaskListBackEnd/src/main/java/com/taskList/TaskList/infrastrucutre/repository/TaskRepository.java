package com.taskList.TaskList.infrastrucutre.repository;

import com.taskList.TaskList.domain.enums.TaskStatusEnum;
import com.taskList.TaskList.domain.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    Optional<List<TaskModel>> findByTaskStatus(TaskStatusEnum status);
}
