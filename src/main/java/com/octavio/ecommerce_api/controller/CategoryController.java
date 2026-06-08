package com.octavio.ecommerce_api.controller;

import com.octavio.ecommerce_api.dto.CategoryDTO;
import com.octavio.ecommerce_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDTO createCategory(
            @Valid @RequestBody CategoryDTO dto) {

        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {

        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(
            @PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO dto) {

        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);
    }
}