package com.tarlley.ToDoList.dto.taskList;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.dto.user.UserDTO;

import java.util.List;

public record TaskListUpdateDTO(Integer id, String name, String description, UserDTO userDTO, List<TaskDTO> taskDTOList) {
}
