package com.tarlley.ToDoList.controller;

import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListRegisterDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListUpdateDTO;
import com.tarlley.ToDoList.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("taskList")
public class TaskListController {


    @Autowired
    private TaskListService taskListService;

    @GetMapping
    public ResponseEntity<List<TaskListDTO>> findAllTasks(){
        return ResponseEntity.ok().body(taskListService.findAllTaskLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDTO> findTaskById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(taskListService.findTaskListById(id));
    }

    @PostMapping()
    public ResponseEntity<TaskListDTO> saveNewTask(@RequestBody TaskListRegisterDTO taskListRegisterDTO){
        TaskListDTO createdTask = taskListService.saveNewTaskList(taskListRegisterDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").buildAndExpand(createdTask.getId()).toUri();
        return ResponseEntity.created(location).body(createdTask);
    }

    @PutMapping
    public ResponseEntity<TaskListDTO> updateTask(@RequestBody TaskListUpdateDTO taskListUpdateDTO){
        return ResponseEntity.ok().body(taskListService.updateTaskList(taskListUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id){
        taskListService.deleteTaskListById(id);
        return ResponseEntity.noContent().build();
    }
}
