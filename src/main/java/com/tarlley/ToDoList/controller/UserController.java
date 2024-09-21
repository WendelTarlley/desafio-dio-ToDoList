package com.tarlley.ToDoList.controller;

import com.tarlley.ToDoList.dto.user.UserDTO;
import com.tarlley.ToDoList.dto.user.UserRegisterDTO;
import com.tarlley.ToDoList.dto.user.UserUpdateDTO;
import com.tarlley.ToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserRegisterDTO userRegisterDTO){

        UserDTO createdUser = userService.saveNewUser(userRegisterDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).body(createdUser);
    }
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok().body(userService.updateUser(userUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
