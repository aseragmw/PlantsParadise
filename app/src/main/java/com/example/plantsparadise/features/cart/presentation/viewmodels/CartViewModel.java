package com.example.plantsparadise.features.cart.presentation.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.core.uitls.Constants;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.domain.repository.CartRepository;
import com.example.plantsparadise.features.home.domain.models.Plant;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel {
    private CartRepository cartRepository;
    MutableLiveData<Cart> cart;
    CacheHelper cacheHelper;
    Context context;

    private CartViewModel(CartRepository cartRepository, Context context) {
        this.cartRepository = cartRepository;
        this.context = context;
        cacheHelper = new CacheHelper(context);
        cart = new MutableLiveData<>();
        Cart newCart = new Cart((String) cacheHelper.get(Constants.USER_ID_CACHE, "no_id"), new ArrayList<>());
        cart.setValue(cacheHelper.getCachedCart());
        if (cart.getValue() == null) {
            cart.setValue(newCart);
        }
    }


    private static CartViewModel instance;

    public static CartViewModel getInstance(CartRepository cartRepository, Context context) {
        if (instance == null) {
            instance = new CartViewModel(cartRepository,context);
        }
        return instance;
    }

    public void addToCart(Plant plant,int quantity) {
        Cart curentCart = cart.getValue();
        if(curentCart!=null){
            curentCart.addItem(plant,quantity);
            cart.setValue(curentCart);
            cacheHelper.cacheCart(context,cart.getValue());
        }

    }

    public void removeFromCart(Plant plant,int quantity) {
        Cart currentCart = cart.getValue();
        if (currentCart != null) {
            currentCart.removeItem(plant, quantity);
            cart.setValue(currentCart); // Notify observers
            cacheHelper.cacheCart(context, currentCart);
        }
    }

    public double getTotalPrice(){
        return cart.getValue().getTotalPrice();
    }

    public int getItemsCount(){
        return cart.getValue().getItems().size();
    }

    public List<CartItem> getItems(){
        return cart.getValue().getItems();
    }

    public MutableLiveData<Cart> getCart(){
        return cart;
    }
}
