package com.store.Ecom.Service;

import com.store.Ecom.Entity.Cart;
import com.store.Ecom.Repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart cartDetails) {
        return cartRepository.findById(id).map((cart) -> {
            cart.setUser((cartDetails.getUser()));
            cart.setCartItems(cartDetails.getCartItems());
            return cartRepository.save(cart);
        }).orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
