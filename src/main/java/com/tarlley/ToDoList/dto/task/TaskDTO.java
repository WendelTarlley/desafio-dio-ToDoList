package com.tarlley.ToDoList.dto.task;

import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
import com.tarlley.ToDoList.model.Category;
import com.tarlley.ToDoList.model.TaskList;
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
  private TaskList taskList;
  private List<Category> categories;
}
