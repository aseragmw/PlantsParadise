package com.example.plantsparadise.core.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.plantsparadise.features.cart.domain.models.Cart;
import com.example.plantsparadise.features.cart.domain.models.CartItem;
import com.google.gson.Gson;

public class CacheHelper {

    private static final String CACHE_NAME = "plants_paradise";

    private final SharedPreferences sharedPreferences;

    public CacheHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
    }

    public void save(String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.apply();
    }

    public Object get(String key, Object defaultValue) {
        if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        }
        return null;
    }

    public void clearKey(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public  void cacheCart(Context context, Cart myObject) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myObject);
        editor.putString("userCart", json);
        editor.apply();
    }

    public Cart getCachedCart() {
        String json = sharedPreferences.getString("userCart", null);
        if (json == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, Cart.class);
    }
}
