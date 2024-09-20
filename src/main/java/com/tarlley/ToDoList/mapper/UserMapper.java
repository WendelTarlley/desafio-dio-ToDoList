package com.tarlley.ToDoList.mapper;

import com.tarlley.ToDoList.dto.user.UserDTO;
import com.tarlley.ToDoList.dto.user.UserRegisterDTO;
import com.tarlley.ToDoList.dto.user.UserUpdateDTO;
import com.tarlley.ToDoList.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface UserMapper {

    List<UserDTO> toUsuarioDTO(List<User> users);

    UserDTO toUsuarioDTO(User user);

    User toEntity(UserRegisterDTO userRegisterDTO);

    User toEntity(UserUpdateDTO userUpdateDTO);

    UserRegisterDTO toUserRegisterDTO(User user);
}
