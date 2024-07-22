package com.example.plantsparadise.features.home.data.datasource.remote;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.home.domain.models.Plant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeRemoteDataSourceWithFirestore implements HomeRemoteDataSource{
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public MutableLiveData<List<Plant>> getPlants() {
        MutableLiveData<List<Plant>> plantsLiveData = new MutableLiveData<>();
        List<Plant> plants = new ArrayList<>();
        db.collection("plants").get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HomeRemoteDataSource", "onFailure: " + e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshot: queryDocumentSnapshots){
                    Log.d("HomeRemoteDataSource", "onSuccess: " + snapshot.getId());
                    Plant plant = snapshot.toObject(Plant.class);
                    plants.add(plant);
                }
                plantsLiveData.setValue(plants);
            }
        });
        Log.d("HomeRemoteDataSource", "getPlants: " + plants.size());
        return plantsLiveData;
    }
}
