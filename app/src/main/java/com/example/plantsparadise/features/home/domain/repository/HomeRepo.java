package com.example.plantsparadise.features.home.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.domain.models.Plant;

import java.util.List;

public interface HomeRepo {
    MutableLiveData<List<Plant>> getPlants();
}
