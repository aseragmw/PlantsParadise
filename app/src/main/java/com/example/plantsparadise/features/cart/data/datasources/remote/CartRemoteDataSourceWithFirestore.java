package com.example.plantsparadise.features.cart.data.datasources.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CartRemoteDataSourceWithFirestore implements CartRemoteDatasource{
    FirebaseFirestore db  = FirebaseFirestore.getInstance();
    String uid =FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    public void placeOrder(Cart cart) {

        db.collection("users").document(uid).collection("orders").add(cart).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("CartRemoteDataSource", "onFailure: "+e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("CartRemoteDataSource", "onSuccess: "+documentReference.getId());
            }
        });

    }
}
