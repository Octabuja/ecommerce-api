package com.octavio.ecommerce_api.repository;

import com.octavio.ecommerce_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}
