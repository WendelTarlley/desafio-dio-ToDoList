package com.tarlley.ToDoList.service;

import com.tarlley.ToDoList.dto.category.CategoryDTO;
import com.tarlley.ToDoList.dto.category.CategoryRegisterDTO;
import com.tarlley.ToDoList.dto.category.CategoryUpdateDTO;
import com.tarlley.ToDoList.exceptions.GlobalNotFoundException;
import com.tarlley.ToDoList.mapper.CategoryMapper;
import com.tarlley.ToDoList.model.Category;
import com.tarlley.ToDoList.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> findAllCategories(){
        return categoryMapper.toDTO(findAllCategories());
    }

    public CategoryDTO findCategoryById(Integer id){
        return categoryMapper.toDTO(findById(id));
    }

    public CategoryDTO saveNewCategory(CategoryRegisterDTO categoryRegisterDTO){
        Category entity = save(categoryMapper.toEntity(categoryRegisterDTO));
        return categoryMapper.toDTO(entity);
    }

    public CategoryDTO updateCategory(CategoryUpdateDTO categoryUpdateDTO){
        if(categoryUpdateDTO.id() == null){
            throw new IllegalArgumentException("Category ID Not Provided.");
        }
        findById(categoryUpdateDTO.id());
        Category entity = categoryRepository.save(categoryMapper.toEntity(categoryUpdateDTO));
        return categoryMapper.toDTO(entity);
    }

    public void deleteCategory(Integer id){
        findById(id);
        categoryRepository.deleteById(id);
    }
    private List<Category> findAll() {
        return categoryRepository.findAll();
    }

    private Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new GlobalNotFoundException("Category not found."));
    }

    private Category save(Category category) {
        if(category.getName()== null || category.getName().isEmpty()){
            throw new IllegalArgumentException("Blank or null name.");
        }

        return categoryRepository.save(category);
    }

    private void deleteById(Integer id) {
        findById(id);
        categoryRepository.deleteById(id);
    }
}
