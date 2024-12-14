package com.store.Ecom.Controller;

import com.store.Ecom.Entity.CartItem;
import com.store.Ecom.Service.CartItemService;
import com.store.Ecom.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts/items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Optional<CartItem> getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody() CartItem cartItem) {
        try {CartItem updatedCartItem = cartItemService.updateCartItem(id, cartItem);
        return ResponseEntity.ok(updatedCartItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
