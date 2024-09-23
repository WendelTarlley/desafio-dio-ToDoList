package com.tarlley.ToDoList.dto.category;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.model.Task;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private Integer id;
    private String name;
    private String description;
    private List<TaskDTO> tasks;
}
