package com.octavio.ecommerce_api.service;

import com.octavio.ecommerce_api.dto.*;
import com.octavio.ecommerce_api.exception.ResourceNotFoundException;
import com.octavio.ecommerce_api.model.Cart;
import com.octavio.ecommerce_api.model.CartItem;
import com.octavio.ecommerce_api.model.Product;
import com.octavio.ecommerce_api.repository.CartRepository;
import com.octavio.ecommerce_api.repository.CartItemRepository;
import com.octavio.ecommerce_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartDTO createCart() {

        Cart cart = new Cart();

        Cart savedCart =
                cartRepository.save(cart);

        return new CartDTO(
                savedCart.getId()
        );
    }

    public void addItemToCart(
            Long cartId,
            AddCartItemDTO dto) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found"));

        Product product = productRepository.findById(
                        dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));

        for (CartItem item : cart.getItems()) {

            if (item.getProduct().getId()
                    .equals(product.getId())) {

                item.setQuantity(
                        item.getQuantity()
                                + dto.getQuantity());

                cartItemRepository.save(item);

                return;
            }
        }

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(
                dto.getQuantity());

        cartItemRepository.save(cartItem);
    }

    public CartResponseDTO getCartById(Long id) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found"));

        List<CartItemDTO> itemsDto =
                cart.getItems()
                        .stream()
                        .map(cartItem -> new CartItemDTO(
                                cartItem.getProduct().getId(),
                                cartItem.getQuantity()
                        ))
                        .collect(Collectors.toList());

        return new CartResponseDTO(
                cart.getId(),
                itemsDto
        );
    }

    public void removeItemFromCart(
            Long cartId,
            Long itemId) {

        CartItem cartItem = getValidatedCartItem(cartId,itemId);

        cartItemRepository.delete(cartItem);
    }

    public void updateCartItem(
            Long cartId,
            Long itemId,
            UpdateCartItemDTO dto) {

        CartItem cartItem = getValidatedCartItem(cartId,itemId);

        cartItem.setQuantity(
                dto.getQuantity());

        cartItemRepository.save(cartItem);
    }

    private CartItem getValidatedCartItem(
            Long cartId,
            Long itemId){

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found"));

        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart item not found"));

        if (!cartItem.getCart().getId().equals(cart.getId())) {

            throw new ResourceNotFoundException(
                    "Cart item not found");
        }
        return cartItem;
    }
}

