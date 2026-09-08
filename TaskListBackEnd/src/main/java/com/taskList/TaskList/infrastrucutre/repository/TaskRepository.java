package com.taskList.TaskList.infrastrucutre.repository;

import com.taskList.TaskList.domain.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
