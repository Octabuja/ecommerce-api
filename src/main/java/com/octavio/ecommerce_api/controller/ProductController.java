package com.octavio.ecommerce_api.controller;

import com.octavio.ecommerce_api.dto.ProductDTO;
import com.octavio.ecommerce_api.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO createProduct(
            @Valid @RequestBody ProductDTO dto) {

        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ProductDTO> getProducts() {

        return productService.getProducts();
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> getProductsByCategory(
            @PathVariable Long id) {

        return productService.getProductsByCategory(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO dto) {

        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);
    }
}