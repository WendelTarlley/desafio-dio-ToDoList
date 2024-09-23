package com.tarlley.ToDoList.dto.task;

import com.tarlley.ToDoList.dto.category.CategoryDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public record TaskResponseDTO(
    Integer id,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    LocalDateTime creationDate,
    LocalDateTime completionDate){
}
