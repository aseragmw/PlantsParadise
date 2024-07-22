package com.example.plantsparadise.features.history.domain.models;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.home.domain.models.Plant;

public class HistoryModel {
    private Cart cart;

    public HistoryModel(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
