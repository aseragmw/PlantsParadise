package com.example.plantsparadise.features.home.presentation.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.plantsparadise.R;
import com.example.plantsparadise.databinding.PlantItemCardBinding;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;
import com.example.plantsparadise.features.home.domain.models.Plant;

import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.PlantViewHolder> {
    List<Plant> plantsList;
    Context context;
    CartViewModel cartViewModel;


    public PlantsAdapter(List<Plant> plantsList, Context context,CartViewModel cartViewModel) {
        this.plantsList = plantsList;
        this.context = context;
        this.cartViewModel = cartViewModel;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantItemCardBinding binding = PlantItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantsList.get(position);
        holder.binding.plantItemTitle.setText(plant.getTitle());
        holder.binding.plantItemDescription.setText(plant.getDescription());
        holder.binding.plantItemPrice.setText(Double.toString(plant.getPrice()));
        Glide.with(context).load(plant.getImgURL()).transform(new RoundedCorners(30)).into(holder.binding.plantItemImage);
        holder.binding.plantItemAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.addToCart(plant,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantsList.size();
    }

    public void setPlantsList(List<Plant> plantsList) {
        this.plantsList = plantsList;
        notifyDataSetChanged();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        private final PlantItemCardBinding binding;
        public PlantViewHolder(@NonNull PlantItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
