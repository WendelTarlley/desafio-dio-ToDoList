package com.tarlley.ToDoList.service;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.dto.task.TaskRegisterDTO;
import com.tarlley.ToDoList.dto.task.TaskUpdateDTO;
import com.tarlley.ToDoList.exceptions.GlobalNotFoundException;
import com.tarlley.ToDoList.mapper.TaskMapper;
import com.tarlley.ToDoList.model.Task;
import com.tarlley.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;


    public TaskDTO saveNewTask(TaskRegisterDTO taskDTO){
        Task task = saveTask(taskMapper.toEntity(taskDTO));
        return taskMapper.toTaskDTO(task);
    }

    public List<TaskDTO> findAllTasks(){
        return taskMapper.toTaskDTO(findAll());
    }

    public TaskDTO findTaskById(Integer id){
        return taskMapper.toTaskDTO(findById(id));
    }

    private List<Task> findAll(){
        return taskRepository.findAll();
    }

    private Task findById(Integer id){
        return taskRepository.findById(id).orElseThrow(() -> new GlobalNotFoundException("Task not found!"));
    }

    private Task saveTask(Task task){
        if(task.getTitle()== null || task.getTitle().isEmpty()){
            throw new IllegalArgumentException("Blank or null title.");
        } else if (task.getStatus() == null) {
            throw new IllegalArgumentException("Invalid status field.");
        } else if (task.getCreationDate() == null ) {
            throw new IllegalArgumentException("Invalid creation date.");
        }

        return taskRepository.save(task);
    }

    public TaskDTO updateTask(TaskUpdateDTO taskUpdateDTO) {
        if(taskUpdateDTO.id() == null){
            throw new IllegalArgumentException("Task ID Not Provided.");
        }

        findById(taskUpdateDTO.id());
        Task entity = taskRepository.save(taskMapper.toEntity(taskUpdateDTO));

        return taskMapper.toTaskDTO(entity);

    }

    public void deleteTask(Integer id){
        Task entity = findById(id);
        taskRepository.delete(entity);

    }
}
