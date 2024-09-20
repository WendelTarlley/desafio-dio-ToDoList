package com.tarlley.ToDoList.dto.task;

import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
import com.tarlley.ToDoList.model.Category;
import com.tarlley.ToDoList.model.TaskList;

import java.time.LocalDateTime;
import java.util.List;

public record TaskUpdateDTO(Integer id, String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime completionDate, TaskList taskList, List<Category> categories) {
}
