package com.taskList.TaskList.shared.serviceUtils;

import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.model.TaskModel;

import java.util.List;

public interface TaskUtils {

    List<TaskDTO> transformTaskModelsToDTOs(List<TaskModel> taskModels);

}
