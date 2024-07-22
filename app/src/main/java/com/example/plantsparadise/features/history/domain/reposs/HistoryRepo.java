package com.example.plantsparadise.features.history.domain.reposs;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.cart.domain.models.Cart;

import java.util.List;

public interface HistoryRepo {
    MutableLiveData<List<Cart>> getHistory();
}
