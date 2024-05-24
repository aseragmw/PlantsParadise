package com.example.plantsparadise.features.auth.data.datasources.remote;

import androidx.annotation.NonNull;

import com.example.plantsparadise.core.callbacks.SuccessOrFailureCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class FirebaseAuth {
    private final com.google.firebase.auth.FirebaseAuth auth = com.google.firebase.auth.FirebaseAuth.getInstance();
    private static FirebaseAuth instance;
    private FirebaseAuth(){}
    public static FirebaseAuth getInstance(){
        if(instance==null){
            instance = new FirebaseAuth();
        }
        return instance;
    }

    public void signInWithEmailPassword(String email, String password, SuccessOrFailureCallback callBack){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                callBack.onSuccess(authResult.getUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onFailure(e);
            }
        });
    }

    public void registerWithEmailPassword(String fullName,String email,String password,SuccessOrFailureCallback callBack){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                authResult.getUser().updateProfile(new com.google.firebase.auth.UserProfileChangeRequest.Builder().setDisplayName(fullName).build()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callBack.onSuccess(authResult.getUser());
                    }
                }).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                callBack.onFailure(e);
                            }
                        }
                );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onFailure(e);
            }
        });
    }

    public boolean isUserLoggedIn(){
        return auth.getCurrentUser()!=null;
    }

    public com.google.firebase.auth.FirebaseUser getCurrentUser(){
        return auth.getCurrentUser();
    }

    public void signOut(SuccessOrFailureCallback callback){
        auth.signOut();
    }
}
