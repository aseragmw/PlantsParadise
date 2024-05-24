package com.example.plantsparadise.features.cart.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.plantsparadise.databinding.CartItemCardBinding;
import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.example.plantsparadise.features.cart.presentation.callbacks.UpdadeCartCallback;
import com.example.plantsparadise.features.cart.presentation.viewmodels.CartViewModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    Context context;
    CartViewModel cartViewModel;
    public CartAdapter(Context context, CartViewModel cartViewModel ) {
        this.context = context;
        this.cartViewModel = cartViewModel;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemCardBinding binding = CartItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartViewModel.getCart().getValue().getItems().get(position);
        holder.binding.plantTitle.setText( cartItem.getPlant().getTitle());
        holder.binding.plantItemCount.setText(Integer.toString(cartItem.getQuantity())+" items");
        holder.binding.plantItemPrice.setText(String.format("%.2f",cartItem.getPlant().getPrice() * cartItem.getQuantity())+"EGP");
        holder.binding.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.addToCart(cartItem.getPlant(),1);
                notifyItemChanged(position);
            }
        });
        holder.binding.removeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.removeFromCart(cartItem.getPlant(),1);
                notifyItemChanged(position);
            }
        });
        holder.binding.plantItemPrice.setText(cartItem.getPrice() * cartItem.getQuantity()+"EGP");
        Glide.with(context).load(cartItem.getPlant().getImgURL()).transform(new RoundedCorners(300)).into(holder.binding.imageView);
    }

    @Override
    public int getItemCount() {
        return cartViewModel.getCart().getValue().getItems().size();
    }

    public void setCartItemList(Cart cart) {
        this.cartViewModel.getCart().setValue(cart);
        notifyDataSetChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CartItemCardBinding binding;
        public CartViewHolder(@NonNull CartItemCardBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
