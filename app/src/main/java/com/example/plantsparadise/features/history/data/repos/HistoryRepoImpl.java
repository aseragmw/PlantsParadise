package com.example.plantsparadise.features.history.data.repos;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.history.data.datasources.remote.HistoryRemoteDataSource;
import com.example.plantsparadise.features.history.domain.reposs.HistoryRepo;

import java.util.List;

public class HistoryRepoImpl implements HistoryRepo {
    HistoryRemoteDataSource remoteDataSource;
    public HistoryRepoImpl(HistoryRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
    @Override
    public MutableLiveData<List<Cart>> getHistory() {
        return remoteDataSource.getHistory();
    }
}
