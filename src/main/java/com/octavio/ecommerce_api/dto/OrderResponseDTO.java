package com.octavio.ecommerce_api.dto;

import java.util.List;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long id;

    private Double total;

    private String status;

    private LocalDateTime createdAt;

    private List<OrderItemDTO> items;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(
            Long id,
            LocalDateTime createdAt,
            List<OrderItemDTO> items,
            String status,
            Double total){

        this.id = id;
        this.items = items;
        this.createdAt = createdAt;
        this.total = total;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
