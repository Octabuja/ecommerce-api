package com.octavio.ecommerce_api.dto;

public class CartDTO {

    private Long id;

    public CartDTO() {
    }

    public CartDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
