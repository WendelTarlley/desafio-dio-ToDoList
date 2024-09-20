package com.tarlley.ToDoList.dto.user;

import com.tarlley.ToDoList.model.TaskList;
import java.util.List;

public record UserUpdateDTO(Integer id, String name, String email, String password, List<TaskList> taskLists) {
}
