package com.octavio.ecommerce_api.service;

import com.octavio.ecommerce_api.dto.OrderResponseDTO;
import com.octavio.ecommerce_api.exception.ResourceNotFoundException;
import com.octavio.ecommerce_api.model.Cart;
import com.octavio.ecommerce_api.model.CartItem;
import com.octavio.ecommerce_api.model.Order;
import com.octavio.ecommerce_api.model.OrderItem;
import com.octavio.ecommerce_api.repository.CartRepository;
import com.octavio.ecommerce_api.repository.OrderItemRepository;
import com.octavio.ecommerce_api.repository.OrderRepository;

import java.time.LocalDateTime;

public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository){

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderResponseDTO createOrder(
            Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found"));

        Order order = new Order();

        order.setCreatedAt(
                LocalDateTime.now());

        order.setStatus("CREATED");

        Double total = 0.0;

        Order savedOrder =
                orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);

            orderItem.setProduct(
                    cartItem.getProduct());

            orderItem.setQuantity(
                    cartItem.getQuantity());

            orderItem.setUnitPrice(
                    cartItem.getProduct().getPrice());

            total += (
                    cartItem.getProduct().getPrice()
                            * cartItem.getQuantity()
            );

            orderItemRepository.save(orderItem);
        }

        savedOrder.setTotal(total);

        savedOrder =
                orderRepository.save(savedOrder);

        return null;
    }
}
