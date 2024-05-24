package com.example.plantsparadise.features.auth.domain.repository;

import com.example.plantsparadise.core.callbacks.SuccessOrFailureCallback;
import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.auth.domain.models.UserModel;
import com.google.firebase.auth.FirebaseUser;

public interface AuthRepository {
    public void login(String email, String password, SuccessOrFailureCallback callback);
    public void register(String username,String email,String password, SuccessOrFailureCallback callback);
    public void logout(SuccessOrFailureCallback callback);
    public UserModel getCurrentUser();
    public boolean isLoggedIn();
}
