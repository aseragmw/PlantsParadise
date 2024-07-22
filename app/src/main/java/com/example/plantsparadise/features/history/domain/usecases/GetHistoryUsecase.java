package com.example.plantsparadise.features.history.domain.usecases;

import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.history.domain.reposs.HistoryRepo;

import java.util.List;

public class GetHistoryUsecase {
    HistoryRepo historyRepo;
    public GetHistoryUsecase(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }
    public MutableLiveData<List<Cart>> execute(){
       return historyRepo.getHistory();
    }
}
