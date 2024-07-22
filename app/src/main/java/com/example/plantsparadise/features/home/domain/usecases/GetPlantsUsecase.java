package com.example.plantsparadise.features.home.domain.usecases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.domain.models.Plant;
import com.example.plantsparadise.features.home.domain.repository.HomeRepo;

import java.util.List;

public class GetPlantsUsecase {
    private final HomeRepo homeRepo;

    public GetPlantsUsecase(HomeRepo repo) {
        homeRepo = repo;
    }

    public MutableLiveData<List<Plant>> execute() {
        return homeRepo.getPlants();
    }
}
