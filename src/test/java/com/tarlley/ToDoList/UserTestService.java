package com.tarlley.ToDoList;

import com.tarlley.ToDoList.dto.UserDTO;
import com.tarlley.ToDoList.dto.UserRegisterDTO;
import com.tarlley.ToDoList.dto.UserUpdateDTO;
import com.tarlley.ToDoList.exceptions.UserNotFoundException;
import com.tarlley.ToDoList.mapper.UserMapper;
import com.tarlley.ToDoList.model.User;
import com.tarlley.ToDoList.repository.UserRepository;
import com.tarlley.ToDoList.service.UserService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserTestService {

    @Mock
    private UserRepository userRepository;


    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper .class);

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldThrowAnUserNotFoundException(){

        Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.findUserById(id));
        assertEquals("User not found with id: "+ id,exception.getMessage());
    }

    @Test
    void shouldThrowAnRuntimeException(){
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(null,"Wendel Tarlley","wendel@wendel.com",null,null);
        RuntimeException exception = assertThrows(RuntimeException.class,() -> userService.atualizarUsuario(userUpdateDTO));
        assertEquals("User ID not provided!",exception.getMessage());
    }
    @Test
    void shouldReturnListOFUsers(){
        User user = criarUsuarios(null,"Usuario 1","Email@email1.com","teste1");

        User user1 = criarUsuarios(null,"Usuario 2","Email@email2.com","teste2");

        List<UserDTO> usuarioDTO = userMapper.toUsuarioDTO(Arrays.asList(user, user1));

        when(userRepository.findAll()).thenReturn(Arrays.asList(user,user1));

        assertEquals(usuarioDTO,userService.findAllUsers());
    }
    @Test
    void shouldReturnUser(){
        Integer id = 1;
        User user = criarUsuarios(id,"Usuario 1","Email@email1.com","teste1");

        UserDTO usuarioDTO = userMapper.toUsuarioDTO(user);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDTO userById = userService.findUserById(id);
        assertEquals(usuarioDTO,userById);
    }

    @Test
    void shouldNotSaveInvalidUser(){
        User user = criarUsuarios(null,null,"Email@email1.com","teste1");

        UserRegisterDTO userRegisterDTO = userMapper.toUserRegisterDTO(user);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.salvarUsuario(userRegisterDTO));
        assertEquals("Incomplete information. Review User registratio!",exception.getMessage());

    }

    User criarUsuarios(Integer id, String nome, String email, String senha){
        return new User(id,nome,senha,email,null);
    }
}
