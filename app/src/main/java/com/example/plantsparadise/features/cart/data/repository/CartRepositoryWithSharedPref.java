package com.example.plantsparadise.features.cart.data.repository;

import android.content.Context;

import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.domain.repository.CartRepository;

public class CartRepositoryWithSharedPref implements CartRepository {
    private CacheHelper cacheHelper;
    private Context context;
    public CartRepositoryWithSharedPref(CacheHelper cacheHelper, Context context) {
        this.cacheHelper = cacheHelper;
    }

    @Override
    public Cart GetCart() {
        return cacheHelper.getCachedCart();
    }

    @Override
    public void SaveCart(Cart cart) {
        cacheHelper.cacheCart(context,cart);
    }

    @Override
    public void placeOrder(Cart cart) {

    }

}
