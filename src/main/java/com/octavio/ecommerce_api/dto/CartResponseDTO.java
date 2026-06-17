package com.octavio.ecommerce_api.dto;

import java.util.List;

public class CartResponseDTO {

    private Long id;

    private List<CartItemDTO> items;

    public CartResponseDTO() {
    }

    public CartResponseDTO(
            Long id,
            List<CartItemDTO> items) {

        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(
            List<CartItemDTO> items) {

        this.items = items;
    }

}