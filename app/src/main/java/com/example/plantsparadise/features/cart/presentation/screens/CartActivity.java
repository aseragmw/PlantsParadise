package com.example.plantsparadise.features.cart.presentation.screens;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsparadise.R;
import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.databinding.ActivityCartBinding;
import com.example.plantsparadise.features.cart.data.repository.CartRepositoryWithSharedPref;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.adapters.CartAdapter;
import com.example.plantsparadise.features.cart.presentation.callbacks.UpdadeCartCallback;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;
import com.example.plantsparadise.features.home.domain.models.Plant;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<CartItem> cartItems;
    RecyclerView rv;
    ActivityCartBinding binding;

    CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CacheHelper cacheHelper = new CacheHelper(this);
        cartViewModel = CartViewModel.getInstance(new CartRepositoryWithSharedPref(cacheHelper,this),this);
        setupCartItems();
        setupRV();
        setupViews();
        cartViewModel.getCart().observe(this, new Observer<Cart>() {

            @Override
            public void onChanged(Cart cart) {
                setupCartItems();
                setupViews();
                setupRV();
            }
        });
    }

    private void setupViews() {
        binding.cartCount.setText(cartItems.size()+"");
        binding.deliveryAmountValue.setText("100EGP");
        binding.totalAmountValue.setText(String.format("%.2f",cartViewModel.getTotalPrice())+"EGP");
    }

    private void setupRV() {
        rv = binding.rvCart;
        CartAdapter adapter = new CartAdapter(this,cartViewModel);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void setupCartItems() {
        cartItems = cartViewModel.getCart().getValue().getItems();
    }


}