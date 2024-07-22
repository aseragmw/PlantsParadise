package com.example.plantsparadise.features.home.presentation.screens;

import static com.example.plantsparadise.core.uitls.Constants.USER_ID_CACHE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.plantsparadise.R;
import com.example.plantsparadise.core.uitls.CacheHelper;
import com.example.plantsparadise.databinding.ActivityHomeBinding;
import com.example.plantsparadise.databinding.PlantItemCardBinding;
import com.example.plantsparadise.features.auth.presentation.screens.SplashActivity;
import com.example.plantsparadise.features.cart.data.repository.CartRepositoryWithSharedPref;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.screens.CartActivity;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;
import com.example.plantsparadise.features.history.presentation.screens.OrdersHitoryActivity;
import com.example.plantsparadise.features.home.domain.models.Plant;
import com.example.plantsparadise.features.home.presentation.adapters.PlantsAdapter;
import com.example.plantsparadise.features.home.presentation.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    PlantsAdapter plantsAdapter;
    List<Plant> plantList = new ArrayList<>();
    CartViewModel cartViewModel;
    ActivityHomeBinding binding;
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        homeViewModel = new HomeViewModel();
        homeViewModel.getPlants();
        CacheHelper cacheHelper = new CacheHelper(this);
        cartViewModel = CartViewModel.getInstance(new CartRepositoryWithSharedPref(cacheHelper, this), this);
        setupViewPager();
        setupViews();
        cartViewModel.getCart().observe(this, new Observer<Cart>() {
            @Override
            public void onChanged(Cart cart) {
                binding.cartCount.setText(cartViewModel.getCart().getValue().getItems().size()+"");
                binding.cartCountText.setText(cartViewModel.getCart().getValue().getItems().size()+" items");

            }
        });
    }



    private void setupViews() {
        boolean userLoggedIn =  new CacheHelper(this).contains(USER_ID_CACHE);
        if(userLoggedIn){
            binding.logoutBtn.setVisibility(View.VISIBLE);
            binding.historyBtn.setVisibility(View.VISIBLE);
        }
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheHelper cache = new CacheHelper(HomeActivity.this);
                cache.clearAll();
                Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        binding.historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrdersHitoryActivity.class);
                startActivity(intent);
            }
        });
        if(plantList.isEmpty()){
            binding.backArrow.setVisibility(View.INVISIBLE);
            binding.forwardArrow.setVisibility(View.INVISIBLE);
        }
        binding.backArrow.setOnClickListener(v -> {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()-1,true);

        });

        binding.forwardArrow.setOnClickListener(v -> {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1,true);

        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int index =position+1;
                binding.currentPagerItem.setText(String.valueOf(index));
                if(position==0){
                    binding.backArrow.setVisibility(View.INVISIBLE);
                }
                else{
                    binding.backArrow.setVisibility(View.VISIBLE);
                }
                if(position==plantList.size()-1){
                    binding.forwardArrow.setVisibility(View.INVISIBLE);
                }
                else{
                    binding.forwardArrow.setVisibility(View.VISIBLE);
                }
            }


        });
        binding.cartCount.setText(String.valueOf(cartViewModel.getCart().getValue().getItems().size()));
        binding.cartCountText.setText(cartViewModel.getCart().getValue().getItems().size()+" items");
    }




    private void setupViewPager() {
        plantsAdapter = new PlantsAdapter(plantList,this,cartViewModel);
        binding.viewPager.setAdapter(plantsAdapter);
        homeViewModel.plants.observe(this, new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                plantsAdapter.setPlantsList(plants);
                plantList=plants;
            }
        });

        binding.goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });
    }
}