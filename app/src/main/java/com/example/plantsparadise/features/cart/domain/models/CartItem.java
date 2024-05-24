package com.example.plantsparadise.features.cart.domain.models;

import com.example.plantsparadise.features.home.domain.models.Plant;

public class CartItem {
    private Plant plant;
    private int quantity;
    private double price;

    public CartItem(Plant plant, int quantity, double price) {
        this.plant = plant;
        this.quantity = quantity;
        this.price = price;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "plant=" + plant +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
