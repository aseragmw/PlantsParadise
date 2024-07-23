package com.example.plantsparadise.features.auth.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.example.plantsparadise.databinding.ActivityRegisterBinding;
import com.example.plantsparadise.features.auth.data.datasources.remote.FirebaseAuth;
import com.example.plantsparadise.features.auth.data.repository.AuthRepositoryImplWithFirebase;
import com.example.plantsparadise.features.auth.domain.models.UserModel;
import com.example.plantsparadise.features.auth.presentation.viewmodels.AuthViewModel;
import com.example.plantsparadise.features.home.presentation.screens.HomeActivity;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    AuthViewModel viewModel;
    CacheHelper cacheHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = AuthViewModel.getInstance(new AuthRepositoryImplWithFirebase(FirebaseAuth.getInstance()));
        cacheHelper = new CacheHelper(this);
        setupViews();
    }

    private void setupViews() {
        binding.login.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        binding.registerBtn.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.registerBtn.setVisibility(View.INVISIBLE);
            if (validateInput()) {
                if (binding.password.getText().toString().equals(binding.cPassword.getText().toString())) {
                    viewModel.register(binding.username.getText().toString(), binding.email.getText().toString(), binding.password.getText().toString(), new SuccessOrFailureCallback() {
                        @Override
                        public void onSuccess(Object successObject) {
                            FirebaseUser user = (FirebaseUser) successObject;
                            cacheHelper.save(Constants.USER_EMAIL_CACHE, user.getEmail());
                            cacheHelper.save(Constants.USER_ID_CACHE, user.getUid());
                            cacheHelper.save(Constants.USER_FULLNAME_CACHE, user.getDisplayName());
                            Toast.makeText(RegisterActivity.this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Object errorObject) {
                            Exception msg = (Exception) errorObject;
                            Toast.makeText(RegisterActivity.this, msg.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.registerBtn.setVisibility(View.VISIBLE);
        });
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    public boolean validateInput() {
        return !binding.username.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty() && !binding.email.getText().toString().isEmpty() && !binding.cPassword.getText().toString().isEmpty();
    }
}