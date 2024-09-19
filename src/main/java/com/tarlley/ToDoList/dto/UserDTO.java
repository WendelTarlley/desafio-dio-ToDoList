package com.tarlley.ToDoList.dto;

import com.tarlley.ToDoList.model.TaskList;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private List<TaskList> listasDeTarefas;
}
