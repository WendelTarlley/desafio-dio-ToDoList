package com.tarlley.ToDoList.controller;

import com.tarlley.ToDoList.dto.user.UserDTO;
import com.tarlley.ToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@RequestParam("id") int id){
        return ResponseEntity.ok().body(userService.findUserById(id));
    }
//
//    @PostMapping
//
//    @PutMapping
//
//    @DeleteMapping("/{id}")
}
