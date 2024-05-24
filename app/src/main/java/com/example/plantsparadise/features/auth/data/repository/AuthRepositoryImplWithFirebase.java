package com.example.plantsparadise.features.auth.data.repository;

import com.example.plantsparadise.core.callbacks.SuccessOrFailureCallback;
import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.auth.domain.models.UserModel;
import com.example.plantsparadise.features.auth.domain.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepositoryImplWithFirebase implements AuthRepository {
    private final FirebaseAuth firebaseAuth;

    public AuthRepositoryImplWithFirebase(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void login(String email, String password, SuccessOrFailureCallback callback) {
        firebaseAuth.signInWithEmailPassword(email, password, callback);
    }

    @Override
    public void register(String username, String email, String password, SuccessOrFailureCallback callback) {
        firebaseAuth.registerWithEmailPassword(username, email, password, callback);

    }

    @Override
    public void logout(SuccessOrFailureCallback callback) {
        firebaseAuth.signOut(callback);

    }

    @Override
    public UserModel getCurrentUser() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return new UserModel(user.getUid(), user.getDisplayName(),user.getEmail());
    }

    @Override
    public boolean isLoggedIn() {
        return firebaseAuth.isUserLoggedIn();
    }
}
