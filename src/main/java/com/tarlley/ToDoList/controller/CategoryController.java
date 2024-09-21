package com.tarlley.ToDoList.controller;

import com.tarlley.ToDoList.dto.category.CategoryDTO;
import com.tarlley.ToDoList.dto.category.CategoryRegisterDTO;
import com.tarlley.ToDoList.dto.category.CategoryUpdateDTO;
import com.tarlley.ToDoList.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.findCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRegisterDTO categoryRegisterDTO) {
        CategoryDTO categoryDTO = categoryService.saveNewCategory(categoryRegisterDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(location).body(categoryDTO);
    }

    @PutMapping()
    public ResponseEntity<CategoryDTO> updateCategory( @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
            return ResponseEntity.ok(categoryService.updateCategory(categoryUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
         categoryService.deleteCategory(id);
      return ResponseEntity.noContent().build();
    }
}
