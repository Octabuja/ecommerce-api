package com.octavio.ecommerce_api.dto;

public class OrderItemDTO {

    private Long productId;

    private Integer quantity;

    private Double unitPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(
            Long productId,
            Integer quantity,
            Double unitPrice) {

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
