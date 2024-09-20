package com.tarlley.ToDoList.model;

import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;


    private LocalDateTime creationDate;


    private LocalDateTime completionDate;

    @ManyToOne
    @JoinColumn(name = "lista_de_tarefas_id")
    private TaskList taskList;

    @ManyToMany
    @JoinTable(
            name = "task_category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;


}

