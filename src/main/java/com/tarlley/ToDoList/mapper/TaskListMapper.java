package com.tarlley.ToDoList.mapper;

import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.model.TaskList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper()
public interface TaskListMapper {

    List<TaskListDTO> toTaskListDTO(List<TaskList> taskLists);

    TaskListDTO toTaskListDTO(TaskList taskList);

    TaskList toEntity(TaskListDTO taskListDTO);

}
