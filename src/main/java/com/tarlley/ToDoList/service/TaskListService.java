package com.tarlley.ToDoList.service;

import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.exceptions.GlobalNotFoundException;
import com.tarlley.ToDoList.mapper.TaskListMapper;
import com.tarlley.ToDoList.mapper.TaskMapper;
import com.tarlley.ToDoList.model.TaskList;
import com.tarlley.ToDoList.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired

    public List<TaskListDTO> findAllTaskLists(){
        return taskListMapper.toTaskListDTO(taskListRepository.findAll());
    }

    public TaskListDTO findTaskListById(Integer id){
        return taskListMapper.toTaskListDTO(findById(id));
    }

    public List<TaskListDTO> findTaskListByUserId(Integer userId){
        return taskListMapper.toTaskListDTO(taskListRepository.findByUserId(userId));
    }

    public TaskListDTO saveNewTaskList(TaskListDTO taskListDTO){

        TaskList taskList = saveTaskList(taskListMapper.toEntity(taskListDTO));

        taskList.getTasks().forEach(item -> item.setTaskList(taskList));

        saveTaskList(taskList);

        return taskListMapper.toTaskListDTO(taskList);
    }

    public TaskListDTO updateTaskList(TaskListDTO taskListDTO){
        if (taskListDTO.getId() == null){
            throw new IllegalArgumentException("TaskList ID Not Provided.");
        }
        findById(taskListDTO.getId());
        TaskList taskList = saveTaskList(taskListMapper.toEntity(taskListDTO));
        return taskListMapper.toTaskListDTO(taskList);
    }

    public void deleteTaskListById(Integer id){
        findById(id);
        taskListRepository.deleteById(id);
    }

    private List<TaskList> findAll(){
        return taskListRepository.findAll();
    }

    private TaskList findById(Integer id){
        return taskListRepository.findById(id).orElseThrow(() -> new GlobalNotFoundException("TaskList not found!"));
    }

    private TaskList saveTaskList(TaskList taskList){
        if(taskList.getName() == null || taskList.getName().isEmpty()){
            throw new IllegalArgumentException("Blank or null name.");
        }

        return taskListRepository.save(taskList);
    }

}
