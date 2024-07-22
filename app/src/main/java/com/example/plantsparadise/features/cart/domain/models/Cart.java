package com.example.plantsparadise.features.cart.domain.models;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.domain.models.Plant;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private String uid;

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "uid='" + uid + '\'' +
                ", items=" + items +
                '}';
    }

    private List<CartItem> items;

    public Cart(String uid, List<CartItem> items) {
        this.uid = uid;
        this.items = items;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public void addItem(Plant item, int quantity) {
        for (CartItem cartItem : items) {
            if (cartItem.getPlant().getTitle().equalsIgnoreCase(item.getTitle())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(item, quantity, item.getPrice() * quantity));
    }


    public void removeItem(Plant item,int quantity) {
        for (CartItem cartItem : items) {
            if (cartItem.getPlant().getTitle().equalsIgnoreCase(item.getTitle())) {
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
                if (cartItem.getQuantity() <= 0) {
                    items.remove(cartItem);
                }
                return;
            }
        }

    }
}

