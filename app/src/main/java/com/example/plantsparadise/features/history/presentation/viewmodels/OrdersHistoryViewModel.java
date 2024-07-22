package com.example.plantsparadise.features.history.presentation.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.history.data.datasources.remote.HistoryRemoteDataSourceWithFirestore;
import com.example.plantsparadise.features.history.data.repos.HistoryRepoImpl;
import com.example.plantsparadise.features.history.domain.usecases.GetHistoryUsecase;

import java.util.ArrayList;
import java.util.List;

public class OrdersHistoryViewModel extends ViewModel {
    public MutableLiveData<List<Cart>> orders = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<Cart>>getAllOrders(){
        GetHistoryUsecase getHistoryUsecase = new GetHistoryUsecase(new HistoryRepoImpl(new HistoryRemoteDataSourceWithFirestore()));
        MutableLiveData<List<Cart>> res =  getHistoryUsecase.execute();
        orders = res;
        return orders;
    }
}
