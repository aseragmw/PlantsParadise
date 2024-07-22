package com.example.plantsparadise.features.home.presentation.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantsparadise.features.home.data.datasource.remote.HomeRemoteDataSourceWithFirestore;
import com.example.plantsparadise.features.home.data.repository.HomeRepoImpl;
import com.example.plantsparadise.features.home.domain.models.Plant;
import com.example.plantsparadise.features.home.domain.usecases.GetPlantsUsecase;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<List<Plant>> plants = new MutableLiveData<>(new ArrayList<>());
    public List<Plant> getPlants(){
        GetPlantsUsecase useCase = new GetPlantsUsecase(new HomeRepoImpl(new HomeRemoteDataSourceWithFirestore()));
        plants = useCase.execute();
        return plants.getValue();
    }
}
