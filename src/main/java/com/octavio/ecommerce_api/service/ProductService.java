package com.octavio.ecommerce_api.service;

import com.octavio.ecommerce_api.dto.ProductDTO;
import com.octavio.ecommerce_api.model.Category;
import com.octavio.ecommerce_api.model.Product;
import com.octavio.ecommerce_api.repository.CategoryRepository;
import com.octavio.ecommerce_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO createProduct(ProductDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category);

        productRepository.save(product);

        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                category.getId()
        );
    }

    public List<ProductDTO> getProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {

        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory().getId()
                ))
                .collect(Collectors.toList());
    }
}