package com.example.plantsparadise.features.home.data.datasource.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.domain.models.Plant;

import java.util.List;

public interface HomeRemoteDataSource {
    MutableLiveData<List<Plant>> getPlants();
}
