package com.tarlley.ToDoList.service;

import com.tarlley.ToDoList.dto.user.UserUpdateDTO;
import com.tarlley.ToDoList.dto.user.UserDTO;
import com.tarlley.ToDoList.dto.user.UserRegisterDTO;
import com.tarlley.ToDoList.exceptions.UserNotFoundException;
import com.tarlley.ToDoList.mapper.UserMapper;
import com.tarlley.ToDoList.model.User;
import com.tarlley.ToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findAllUsers(){

        return userMapper.toUsuarioDTO(userRepository.findAll());
    }

    public UserDTO findUserById(Integer id){
        User user = this.findById(id);
        return userMapper.toUsuarioDTO(user);
    }

    public UserDTO saveNewUser(UserRegisterDTO userRegisterDTO){
        userRepository.findByEmail(userRegisterDTO.email()).ifPresent(user -> {
            throw new RuntimeException("Email já cadastrado!");
        });

        if(userRegisterDTO.name() == null || userRegisterDTO.password() == null || userRegisterDTO.email() == null){
            throw new RuntimeException("Incomplete information. Review User registration!");
        }

        User entity  = saveUser(userMapper.toEntity(userRegisterDTO));

        return userMapper.toUsuarioDTO(entity);
    }

    public UserDTO updateUser(UserUpdateDTO userUpdateDTO){

        if(userUpdateDTO.id() == null){
            throw new RuntimeException("User ID not provided!");
        }
        User entity = userRepository.save(userMapper.toEntity(userUpdateDTO));
        return userMapper.toUsuarioDTO(entity);
    }

    public void deleteUser(Integer id){
        User user = findById(id);
        userRepository.delete(user);
    }

    private User findById(Integer id){
        Optional<User> entity = userRepository.findById(id);
        return
                entity.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    private User saveUser(User user){
        return userRepository.save(user);
    }
}
