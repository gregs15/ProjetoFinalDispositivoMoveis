package com.example.dispositivomovel.adapter;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.model.Dish;
import com.example.dispositivomovel.model.Drink;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final List<Parcelable> menuItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddToCartClick(Parcelable item);
    }

    public MenuAdapter(List<Parcelable> menuItems, OnItemClickListener listener) {
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Parcelable item = menuItems.get(position);

        if (item instanceof Dish) {
            Dish dish = (Dish) item;
            holder.name.setText(dish.getName());
            holder.description.setText(dish.getDescription());
            holder.price.setText(String.format("R$ %.2f", dish.getPrice()));
        } else if (item instanceof Drink) {
            Drink drink = (Drink) item;
            holder.name.setText(drink.getName());
            holder.description.setText(drink.getDescription());
            holder.price.setText(String.format("R$ %.2f", drink.getPrice()));
        }

        // Adiciona o item ao carrinho quando o botão for clicado
        holder.addToCartButton.setOnClickListener(v -> listener.onAddToCartClick(item));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;
        Button addToCartButton;

        MenuViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            price = itemView.findViewById(R.id.item_price);
            addToCartButton = itemView.findViewById(R.id.cart_button);
        }
    }
}
