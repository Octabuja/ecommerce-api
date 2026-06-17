package com.octavio.ecommerce_api.controller;

import com.octavio.ecommerce_api.dto.AddCartItemDTO;
import com.octavio.ecommerce_api.dto.CartDTO;
import com.octavio.ecommerce_api.dto.CartResponseDTO;
import com.octavio.ecommerce_api.dto.UpdateCartItemDTO;
import com.octavio.ecommerce_api.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService = cartService;
    }

    @PostMapping
    public CartDTO createCart() {

        return cartService.createCart();
    }

    @PostMapping("/{cartId}/items")
    public void addItemToCart(
            @PathVariable Long cartId,
            @RequestBody AddCartItemDTO dto) {

        cartService.addItemToCart(
                cartId,
                dto);
    }

    @GetMapping("/{id}")
    public CartResponseDTO getCartById(
            @PathVariable Long id) {

        return cartService.getCartById(id);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public void removeItemFromCart(
            @PathVariable Long cartId,
            @PathVariable Long itemId) {

        cartService.removeItemFromCart(
                cartId,
                itemId);
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public void updateCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestBody UpdateCartItemDTO dto) {

        cartService.updateCartItem(
                cartId,
                itemId,
                dto);
    }
}