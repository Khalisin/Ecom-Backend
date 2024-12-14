package com.store.Ecom.Service;

import com.store.Ecom.Entity.CartItem;
import com.store.Ecom.Repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }


    @Transactional
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem cartItemDetails) {
        return cartItemRepository.findById(id).map(cartItem -> {
            cartItem.setCart(cartItemDetails.getCart());
            cartItem.setProduct(cartItemDetails.getProduct());
            cartItem.setQuantity(cartItemDetails.getQuantity());
            return cartItemRepository.save(cartItem);
        }).orElseThrow(() -> new RuntimeException("Cart Item not found with id " + id));
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

}
