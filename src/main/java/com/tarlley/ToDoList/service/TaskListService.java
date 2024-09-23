package com.tarlley.ToDoList.service;

import com.tarlley.ToDoList.dto.taskList.TaskListDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListRegisterDTO;
import com.tarlley.ToDoList.dto.taskList.TaskListUpdateDTO;
import com.tarlley.ToDoList.exceptions.GlobalNotFoundException;
import com.tarlley.ToDoList.mapper.TaskListMapper;
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


    public List<TaskListDTO> findAllTaskLists(){
        return taskListMapper.toTaskListDTO(taskListRepository.findAll());
    }

    public TaskListDTO findTaskListById(Integer id){
        return taskListMapper.toTaskListDTO(findById(id));
    }

    public List<TaskListDTO> findTaskListByUserId(Integer userId){
        return taskListMapper.toTaskListDTO(taskListRepository.findByUserId(userId));
    }

    public TaskListDTO saveNewTaskList(TaskListRegisterDTO taskListRegisterDTO){
        TaskList taskList = saveTaskList(taskListMapper.toEntity(taskListRegisterDTO));
        return taskListMapper.toTaskListDTO(taskList);
    }

    public TaskListDTO updateTaskList(TaskListUpdateDTO taskListUpdateDTO){
        if (taskListUpdateDTO.id() == null){
            throw new IllegalArgumentException("TaskList ID Not Provided.");
        }
        findById(taskListUpdateDTO.id());
        TaskList taskList = saveTaskList(taskListMapper.toEntity(taskListUpdateDTO));
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
