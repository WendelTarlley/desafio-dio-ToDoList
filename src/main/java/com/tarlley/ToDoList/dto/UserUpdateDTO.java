package com.tarlley.ToDoList.dto;

import com.tarlley.ToDoList.model.TaskList;
import java.util.List;

public record UserUpdateDTO(Integer id,String nome,String email,String senha,List<TaskList> listasDeTarefas) {
}
