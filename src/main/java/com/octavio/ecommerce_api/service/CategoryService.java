package com.octavio.ecommerce_api.service;

import com.octavio.ecommerce_api.dto.CategoryDTO;
import com.octavio.ecommerce_api.exception.ResourceNotFoundException;
import com.octavio.ecommerce_api.model.Category;
import com.octavio.ecommerce_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryDTO dto) {

        Category category = new Category();

        category.setName(dto.getName());

        categoryRepository.save(category);

        return new CategoryDTO(category.getName());
    }

    public List<CategoryDTO> getCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDTO(category.getName()))
                .collect(Collectors.toList());
    }
    public CategoryDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"));

        return new CategoryDTO(
                category.getName()
        );
    }

    public CategoryDTO updateCategory(
            Long id,
            CategoryDTO dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"));

        category.setName(dto.getName());

        Category updatedCategory =
                categoryRepository.save(category);

        return new CategoryDTO(
                updatedCategory.getName()
        );
    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"));

        categoryRepository.delete(category);
    }
}
