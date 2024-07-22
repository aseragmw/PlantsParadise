package com.example.plantsparadise.features.cart.presentation.screens;

import static com.example.plantsparadise.core.uitls.Constants.USER_ID_CACHE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import com.example.plantsparadise.features.auth.presentation.screens.LoginActivity;
import com.example.plantsparadise.features.cart.data.datasources.remote.CartRemoteDataSourceWithFirestore;
import com.example.plantsparadise.features.cart.data.repository.CartRepoWithFirestore;
import com.example.plantsparadise.features.cart.data.repository.CartRepositoryWithSharedPref;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.domain.usecases.PlaceOrderUsecase;
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
        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasUser = new CacheHelper(getApplicationContext()).contains(USER_ID_CACHE);
                if(hasUser){
                    cartViewModel.placeOrder();
                    Toast.makeText(CartActivity.this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Login First",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
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