package com.taskList.TaskList.shared.serviceUtils.serviceUtilsImpl;

import com.taskList.TaskList.domain.dto.TaskDTO;
import com.taskList.TaskList.domain.model.TaskModel;
import com.taskList.TaskList.shared.serviceUtils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaria para metodos que possam ser reutilizadas dentro da classe de service das tasks
 *
 * @author ubuntuuser
 * @date 2026/09/30 23:45
 */
@Component
@Slf4j
public class TaskUtilsImpl implements TaskUtils {

    /**
     * Transforma a lista de task Models em uma lista de dtos
     *
     * @param taskModels lista de tasks apartir da classe model
     * @return {@link List<TaskDTO>}
     */
    @Override
    public List<TaskDTO> transformTaskModelsToDTOs(List<TaskModel> taskModels) {
        if(taskModels==null || taskModels.isEmpty()){
            return new ArrayList<>();
        }
        return taskModels.stream().map(TaskModel::toDTO).toList();
    }
}
