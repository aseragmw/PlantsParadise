package com.example.plantsparadise.features.history.data.datasources.remote;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryRemoteDataSourceWithFirestore implements HistoryRemoteDataSource{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public MutableLiveData<List<Cart>> getHistory() {
        Log.d("HistoryRemoteDataSource", "hola: ");
        MutableLiveData<List<Cart>> orders = new MutableLiveData<>(new ArrayList<>());
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("users").document(uid).collection("orders").get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("HistoryRemoteDataSource", "getHistory: "+e.toString());
            }
        }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Cart> history = new ArrayList<>();
                for(DocumentSnapshot doc:queryDocumentSnapshots){
                    Cart order = doc.toObject(Cart.class);
                    history.add(order);
                    Log.d("HistoryRemoteDataSource", "onSuccess: "+order.toString());
                }
                orders.setValue(history);
            }
        });
        return orders;
    }
}
