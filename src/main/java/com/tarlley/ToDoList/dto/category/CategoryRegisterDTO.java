package com.tarlley.ToDoList.dto.category;

import com.tarlley.ToDoList.model.Task;

import java.util.List;

public record CategoryRegisterDTO(String name, String description, List<Task> tasks) {
}
