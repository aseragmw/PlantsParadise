package com.example.plantsparadise.features.auth.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.plantsparadise.R;
import com.example.plantsparadise.core.callbacks.SuccessOrFailureCallback;
import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.core.uitls.Constants;
import com.example.plantsparadise.databinding.ActivityLandingBinding;
import com.example.plantsparadise.databinding.ActivityLoginBinding;
import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.auth.data.repository.AuthRepositoryImplWithFirebase;
import com.example.plantsparadise.features.auth.domain.models.UserModel;
import com.example.plantsparadise.features.auth.presentation.viewmodels.AuthViewModel;
import com.example.plantsparadise.features.home.presentation.screens.HomeActivity;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    AuthViewModel viewModel ;
    CacheHelper cacheHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel =  AuthViewModel.getInstance(new AuthRepositoryImplWithFirebase(FirebaseAuth.getInstance()));
        cacheHelper = new CacheHelper(this);
        setupView();
    }

    private void setupView() {
        binding.createAccount.setOnClickListener(v -> {
            startActivity(new Intent(this,RegisterActivity.class));
            finish();
        });
        binding.loginBtn.setOnClickListener(v -> {
            if (validateInput()) {
                viewModel.login(binding.username.getText().toString(), binding.password.getText().toString(), new SuccessOrFailureCallback() {
                    @Override
                    public void onSuccess(Object successObject) {
                        FirebaseUser user = (FirebaseUser) successObject;
                        cacheHelper.save(Constants.USER_EMAIL_CACHE,user.getEmail());
                        cacheHelper.save(Constants.USER_ID_CACHE,user.getUid());
                        cacheHelper.save(Constants.USER_FULLNAME_CACHE,user.getDisplayName());
                        Toast.makeText(LoginActivity.this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Object errorObject) {
                        Exception msg = (Exception) errorObject;
                        Toast.makeText(LoginActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    public boolean validateInput() {
        return !binding.username.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty();
    }
}