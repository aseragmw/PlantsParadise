package com.example.plantsparadise.features.cart.domain.usecases;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.repository.CartRepository;

public class PlaceOrderUsecase {
    final CartRepository cartRepository;

    public PlaceOrderUsecase(CartRepository repo) {
        cartRepository = repo;
    }

    public void execute(Cart cart) {
        cartRepository.placeOrder(cart);
    }
}
