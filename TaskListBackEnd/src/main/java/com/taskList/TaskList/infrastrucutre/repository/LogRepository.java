package com.taskList.TaskList.infrastrucutre.repository;

import com.taskList.TaskList.domain.model.LogModel;
import com.taskList.TaskList.domain.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogModel, Long> {
    void deleteBytask(TaskModel taskId);
}
