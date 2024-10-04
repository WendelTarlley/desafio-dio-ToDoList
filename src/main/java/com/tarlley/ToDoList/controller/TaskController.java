package com.tarlley.ToDoList.controller;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAllTasks(){
        return ResponseEntity.ok().body(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(taskService.findTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> saveNewTask(@RequestBody TaskDTO taskDTO){
        TaskDTO createdTask = taskService.saveNewTask(taskDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").buildAndExpand(createdTask.getId()).toUri();
        return ResponseEntity.created(location).body(createdTask);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO){
        return ResponseEntity.ok().body(taskService.updateTask(taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
