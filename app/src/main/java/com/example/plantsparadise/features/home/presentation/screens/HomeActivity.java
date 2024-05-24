package com.example.plantsparadise.features.home.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import com.example.plantsparadise.features.cart.data.repository.CartRepositoryWithSharedPref;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.screens.CartActivity;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;
import com.example.plantsparadise.features.home.domain.models.Plant;
import com.example.plantsparadise.features.home.presentation.adapters.PlantsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    PlantsAdapter plantsAdapter;
    List<Plant> plantList;
    CartViewModel cartViewModel;
    ActivityHomeBinding binding;

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
        CacheHelper cacheHelper = new CacheHelper(this);
        cartViewModel = CartViewModel.getInstance(new CartRepositoryWithSharedPref(cacheHelper, this), this);
        setupPlantsList();
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
                binding.currentPagerItem.setText(""+index);
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
        binding.cartCount.setText(cartViewModel.getCart().getValue().getItems().size()+"");
        binding.cartCountText.setText(cartViewModel.getCart().getValue().getItems().size()+" items");
    }


    private void setupPlantsList() {
        plantList = new ArrayList<>();
        plantList.add(new Plant("Fiddle Leaf Fig", "A large, lush plant perfect for indoor settings.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 49.99));
        plantList.add(new Plant("Snake Plant", "A hardy plant that thrives on neglect.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 29.99));
        plantList.add(new Plant("Monstera Deliciosa", "A plant with unique, split leaves.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 39.99));
        plantList.add(new Plant("Peace Lily", "An elegant plant that blooms with white flowers.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 24.99));
        plantList.add(new Plant("Spider Plant", "A popular plant known for its air-purifying qualities.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 19.99));
        plantList.add(new Plant("Rubber Plant", "A low-maintenance plant with shiny leaves.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 34.99));
        plantList.add(new Plant("ZZ Plant", "A tough plant that can survive in low light.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 27.99));
        plantList.add(new Plant("Pothos", "A versatile plant that can be grown in water or soil.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 14.99));
        plantList.add(new Plant("Aloe Vera", "A medicinal plant known for its healing properties.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 9.99));
        plantList.add(new Plant("Bamboo Palm", "A plant that adds a tropical touch to your home.", "https://wallpapers.com/images/high/microscopic-pictures-1072-x-1072-vqokqzc6iqgksl3m.webp", 45.99));
    }

    private void setupViewPager() {
        plantsAdapter = new PlantsAdapter(plantList,this,cartViewModel);
        binding.viewPager.setAdapter(plantsAdapter);
        binding.goToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });
    }
}