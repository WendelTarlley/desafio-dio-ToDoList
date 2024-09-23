package com.tarlley.ToDoList.dto.user;

import com.tarlley.ToDoList.model.TaskList;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private List<TaskList> taskLists;
}
