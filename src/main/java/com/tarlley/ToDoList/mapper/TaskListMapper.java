package com.tarlley.ToDoList.mapper;

import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListRegisterDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListUpdateDTO;
import com.tarlley.ToDoList.model.TaskList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface TaskListMapper {

    List<TaskListDTO> toTaskListDTO(List<TaskList> taskLists);

    TaskListDTO toTaskListDTO(TaskList taskList);

    TaskList toEntity(TaskListRegisterDTO taskListRegisterDTO);

    TaskList toEntity(TaskListUpdateDTO taskListUpdateDTO);

    List<TaskList> toEntity(List<TaskListUpdateDTO> listUpdateDTOS);
}
