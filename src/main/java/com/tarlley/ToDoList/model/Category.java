package com.tarlley.ToDoList.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Task> tasks;


}
