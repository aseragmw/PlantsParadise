package com.example.plantsparadise.features.cart.data.datasources.remote;

import com.example.plantsparadise.features.cart.domain.models.Cart;

public interface CartRemoteDatasource {
    void placeOrder(Cart cart);
}
