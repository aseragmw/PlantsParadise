package com.example.plantsparadise.features.cart.domain.repository;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;

public interface CartRepository {
    public Cart GetCart();
    public void SaveCart(Cart cart);
    public void placeOrder(Cart cart);
}
