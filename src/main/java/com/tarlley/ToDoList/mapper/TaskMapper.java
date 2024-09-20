package com.tarlley.ToDoList.mapper;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.dto.task.TaskRegisterDTO;
import com.tarlley.ToDoList.dto.task.TaskUpdateDTO;
import com.tarlley.ToDoList.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface TaskMapper {
    
    Task toEntity(TaskDTO taskDTO);

    TaskDTO toTaskDTO(Task task);

    List<TaskDTO> toTaskDTO(List<Task> allTasks);

    TaskRegisterDTO toTaskRegisterDTO(Task task);

    TaskUpdateDTO toTaskUpdateDTO(Task task);

    Task toEntity(TaskRegisterDTO taskDTO);

    Task toEntity(TaskUpdateDTO taskUpdateDTO);
}
