package com.tarlley.ToDoList.mapper;

import com.tarlley.ToDoList.dto.category.CategoryDTO;
import com.tarlley.ToDoList.dto.category.CategoryRegisterDTO;
import com.tarlley.ToDoList.dto.category.CategoryUpdateDTO;
import com.tarlley.ToDoList.dto.user.UserDTO;
import com.tarlley.ToDoList.dto.user.UserRegisterDTO;
import com.tarlley.ToDoList.dto.user.UserUpdateDTO;
import com.tarlley.ToDoList.model.Category;
import com.tarlley.ToDoList.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper()
public interface CategoryMapper {

    List<CategoryDTO> toDTO(List<CategoryDTO> categoryDTOS);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryRegisterDTO categoryRegisterDTO);

    Category toEntity(CategoryUpdateDTO categoryUpdateDTO);
}
