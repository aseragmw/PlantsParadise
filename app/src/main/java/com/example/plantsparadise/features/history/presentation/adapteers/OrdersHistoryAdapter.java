package com.example.plantsparadise.features.history.presentation.adapteers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsparadise.databinding.HistoryItemCardBinding;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.history.presentation.screens.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class OrdersHistoryAdapter extends RecyclerView.Adapter<OrdersHistoryAdapter.ViewHolder> {

    List<Cart> items =new ArrayList<>();

    public OrdersHistoryAdapter(List<Cart> items, Context context) {
        this.items = items;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemCardBinding binding = HistoryItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart item = items.get(position);
        holder.binding.itemCount.setText("Items: "+String.valueOf(item.getItems().size()));
        holder.binding.totalPrice.setText("Total Price: "+String.valueOf(item.getTotalPrice())+" $");
        holder.binding.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderDetailsActivity.class);
                intent.putExtra("item",item);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HistoryItemCardBinding binding;
        public ViewHolder(@NonNull HistoryItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
