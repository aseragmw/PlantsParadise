package com.example.plantsparadise.features.home.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.data.datasource.remote.HomeRemoteDataSource;
import com.example.plantsparadise.features.home.domain.models.Plant;
import com.example.plantsparadise.features.home.domain.repository.HomeRepo;

import java.util.List;

public class HomeRepoImpl implements HomeRepo {
    final HomeRemoteDataSource remoteDataSource;

    public HomeRepoImpl(HomeRemoteDataSource remote) {
        remoteDataSource = remote;
    }

    @Override
    public MutableLiveData<List<Plant>> getPlants() {
        return remoteDataSource.getPlants();
    }
}
