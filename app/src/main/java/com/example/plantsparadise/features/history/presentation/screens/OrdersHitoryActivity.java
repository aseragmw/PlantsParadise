package com.example.plantsparadise.features.history.presentation.screens;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.plantsparadise.R;
import com.example.plantsparadise.databinding.ActivityOrdersHitoryBinding;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.history.presentation.adapteers.OrdersHistoryAdapter;
import com.example.plantsparadise.features.history.presentation.viewmodels.OrdersHistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersHitoryActivity extends AppCompatActivity {
    OrdersHistoryViewModel viewModel;
    List<Cart>items = new ArrayList<>();
    OrdersHistoryAdapter adapter;
    ActivityOrdersHitoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrdersHitoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupRV();
        viewModel = new OrdersHistoryViewModel();
        viewModel.getAllOrders();
        viewModel.orders.observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                for(Cart item :carts){
                    Log.d("inside history screen",item.toString());
                    items = carts;
                    adapter.setItems(carts);
                }
            }
        });
    }

    private void setupRV() {
        adapter = new OrdersHistoryAdapter(items,this);
        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHistory.setAdapter(adapter);
    }
}