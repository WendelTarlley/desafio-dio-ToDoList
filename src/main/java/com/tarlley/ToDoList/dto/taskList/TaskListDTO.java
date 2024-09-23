package com.tarlley.ToDoList.dto.taskList;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.dto.user.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class TaskListDTO {
    Integer id;
    String name;
    String description;
    UserDTO user;
    List<TaskDTO>tasks;
}
