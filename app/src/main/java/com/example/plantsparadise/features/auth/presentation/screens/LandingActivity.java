package com.example.plantsparadise.features.auth.presentation.screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.plantsparadise.R;
import com.example.plantsparadise.databinding.ActivityLandingBinding;
import com.example.plantsparadise.features.home.presentation.screens.HomeActivity;

public class LandingActivity extends AppCompatActivity {
    ActivityLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        binding.loginArrow.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));

        });
        binding.registerArrow.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));

        });
        binding.register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));

        });
        binding.withoutAccountArrow.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
        binding.withoutAccount.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}