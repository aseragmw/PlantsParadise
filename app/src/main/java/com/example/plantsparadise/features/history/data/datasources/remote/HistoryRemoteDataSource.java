package com.example.plantsparadise.features.history.data.datasources.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.cart.domain.models.Cart;

import java.util.List;

public interface HistoryRemoteDataSource {
    MutableLiveData<List<Cart>> getHistory();
}
