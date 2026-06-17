package com.octavio.ecommerce_api.dto;

public class UpdateCartItemDTO {

    private Integer quantity;

    public UpdateCartItemDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Integer quantity) {

        this.quantity = quantity;
    }
}
