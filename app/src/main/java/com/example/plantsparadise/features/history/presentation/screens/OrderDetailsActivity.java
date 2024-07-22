package com.example.plantsparadise.features.history.presentation.screens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsparadise.R;
import com.example.plantsparadise.databinding.ActivityCartBinding;
import com.example.plantsparadise.databinding.ActivityOrderDetailsBinding;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.adapters.CartAdapter;
import com.example.plantsparadise.features.history.presentation.adapteers.OrderDetailsAdapter;

import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    Cart item;
    RecyclerView rv;
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        item = (Cart)getIntent().getSerializableExtra("item");
        if(item!=null){
            setupRV();
        }
        else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private void setupRV() {
        rv = binding.rvOrderDetails;
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(this,item);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
