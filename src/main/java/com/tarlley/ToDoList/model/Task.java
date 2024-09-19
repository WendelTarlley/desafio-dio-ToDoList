package com.tarlley.ToDoList.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String descricao;
    private String status;
    private String prioridade;

    @Temporal(TemporalType.DATE)
    private Date dataDeCriacao;

    @Temporal(TemporalType.DATE)
    private Date dataDeConclusao;

    @ManyToOne
    @JoinColumn(name = "lista_de_tarefas_id")
    private TaskList taskList;

    @ManyToMany
    @JoinTable(
            name = "task_category",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categorias;


}

