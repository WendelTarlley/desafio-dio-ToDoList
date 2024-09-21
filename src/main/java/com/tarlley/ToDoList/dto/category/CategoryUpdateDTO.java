package com.tarlley.ToDoList.dto.category;

import com.tarlley.ToDoList.model.Task;
import jakarta.persistence.ManyToMany;

import java.util.List;

public record CategoryUpdateDTO(Integer id,String name,String description, List<Task> tasks) {
}
