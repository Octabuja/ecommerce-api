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
import com.octavio.ecommerce_api.dto.OrderItemDTO;
import com.octavio.ecommerce_api.repository.CartItemRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository,
                        CartItemRepository cartItemRepository){

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
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

            savedOrder.getItems().add(orderItem);
        }

        savedOrder.setTotal(total);

        savedOrder =
                orderRepository.save(savedOrder);

        cartItemRepository.deleteAll(
                cart.getItems());


        savedOrder =
                orderRepository.findById(
                                savedOrder.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Order not found"));

        List<OrderItemDTO> itemsDto =
                savedOrder.getItems()
                        .stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getUnitPrice()
                        ))
                        .collect(Collectors.toList());

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getCreatedAt(),
                savedOrder.getTotal(),
                savedOrder.getStatus(),
                itemsDto
        );
    }

    public OrderResponseDTO getOrderById(
            Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"));

        List<OrderItemDTO> itemsDto =
                order.getItems()
                        .stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getUnitPrice()
                        ))
                        .collect(Collectors.toList());

        return new OrderResponseDTO(
                order.getId(),
                order.getCreatedAt(),
                order.getTotal(),
                order.getStatus(),
                itemsDto
        );
    }

    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders =
                orderRepository.findAll();

        return orders.stream()
                .map(order -> {

                    List<OrderItemDTO> itemsDto =
                            order.getItems()
                                    .stream()
                                    .map(orderItem -> new OrderItemDTO(
                                            orderItem.getProduct().getId(),
                                            orderItem.getQuantity(),
                                            orderItem.getUnitPrice()
                                    ))
                                    .collect(Collectors.toList());

                    return new OrderResponseDTO(
                            order.getId(),
                            order.getCreatedAt(),
                            order.getTotal(),
                            order.getStatus(),
                            itemsDto
                    );
                })
                .collect(Collectors.toList());
    }
}
