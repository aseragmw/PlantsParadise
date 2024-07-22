package com.example.plantsparadise.features.cart.data.repository;

import com.example.plantsparadise.features.cart.data.datasources.remote.CartRemoteDatasource;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.repository.CartRepository;

public class CartRepoWithFirestore implements CartRepository {
    final CartRemoteDatasource remoteDatasource;

    public CartRepoWithFirestore(CartRemoteDatasource remote) {
        remoteDatasource = remote;
    }

    @Override
    public Cart GetCart() {
        return null;
    }

    @Override
    public void SaveCart(Cart cart) {

    }

    @Override
    public void placeOrder(Cart cart) {
        remoteDatasource.placeOrder(cart);
    }
}
