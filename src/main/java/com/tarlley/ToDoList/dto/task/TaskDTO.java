package com.tarlley.ToDoList.dto.task;

import com.tarlley.ToDoList.dto.category.CategoryDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDTO {
  private Integer id;
  private String title;
  private String description;
  private TaskStatus status;
  private TaskPriority priority;
  private LocalDateTime creationDate;
  private LocalDateTime completionDate;
  private TaskListDTO taskList;
  private List<CategoryDTO> categories;
}
