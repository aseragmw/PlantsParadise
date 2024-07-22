package com.example.plantsparadise.features.auth.presentation.viewmodels;

import com.example.plantsparadise.core.callbacks.SuccessOrFailureCallback;
import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.features.auth.domain.models.UserModel;
import com.example.plantsparadise.features.auth.domain.repository.AuthRepository;

public class AuthViewModel {
    private AuthRepository authRepository;
    private AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    public static AuthViewModel getInstance(AuthRepository authRepository) {
        return new AuthViewModel(authRepository);
    }

    public void login(String email, String password,SuccessOrFailureCallback callback) {
        authRepository.login(email, password,callback);
    }

    public void register(String username,String email, String password,SuccessOrFailureCallback callback) {
        authRepository.register(username,email, password,callback);
    }

    public void logout(SuccessOrFailureCallback callback) {
        authRepository.logout(callback);
    }

    public boolean isLoggedIn() {
        return authRepository.isLoggedIn();
    }

    public UserModel getCurrentUser() {
        return authRepository.getCurrentUser();
    }

}
