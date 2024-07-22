package com.example.plantsparadise.features.history.presentation.adapteers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.plantsparadise.databinding.CartItemCardBinding;
import com.example.plantsparadise.databinding.OrderDetailsCardBinding;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>{

    Context context;
    Cart cart;
    public OrderDetailsAdapter(Context context, Cart cart ) {
        this.context = context;
        this.cart = cart;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderDetailsCardBinding binding = OrderDetailsCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        CartItem cartItem = cart.getItems().get(position);
        holder.binding.plantTitle.setText( cartItem.getPlant().getTitle());
        holder.binding.plantItemCount.setText(Integer.toString(cartItem.getQuantity())+" items");
        holder.binding.plantItemPrice.setText(String.format("%.2f",cartItem.getPlant().getPrice() * cartItem.getQuantity())+"EGP");

        holder.binding.plantItemPrice.setText(cartItem.getPrice() * cartItem.getQuantity()+"EGP");
        Glide.with(context).load(cartItem.getPlant().getImgURL()).transform(new RoundedCorners(300)).into(holder.binding.imageView);
    }

    @Override
    public int getItemCount() {
        return cart.getItems().size();
    }

    public void setCartItemList(Cart cart) {
        this.cart = cart;
        notifyDataSetChanged();
    }

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        OrderDetailsCardBinding binding;
        public OrderDetailsViewHolder(@NonNull OrderDetailsCardBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
