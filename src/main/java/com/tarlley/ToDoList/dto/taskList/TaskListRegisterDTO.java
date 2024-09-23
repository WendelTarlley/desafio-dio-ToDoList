package com.tarlley.ToDoList.dto.taskList;

import com.tarlley.ToDoList.dto.task.TaskResponseDTO;
import com.tarlley.ToDoList.dto.user.UserResponseDTO;

import java.util.List;

public record TaskListRegisterDTO(String name, String description, UserResponseDTO userResponseDTO, List<TaskResponseDTO> taskDTOList) {
}
