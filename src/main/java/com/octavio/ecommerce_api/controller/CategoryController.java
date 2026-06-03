package com.octavio.ecommerce_api.controller;

import com.octavio.ecommerce_api.dto.CategoryDTO;
import com.octavio.ecommerce_api.service.CategoryService;
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
            @RequestBody CategoryDTO dto) {

        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {

        return categoryService.getCategories();
    }
}