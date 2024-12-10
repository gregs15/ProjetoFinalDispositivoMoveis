package com.example.dispositivomovel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.model.Dish;
import com.example.dispositivomovel.model.Drink;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Dish> orderedDishes;
    private List<Drink> orderedDrinks;


    public CartAdapter(List<Dish> orderedDishes, List<Drink> orderedDrinks) {
        this.orderedDishes = orderedDishes;
        this.orderedDrinks = orderedDrinks;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        if (position < orderedDishes.size()) {
            Dish dish = orderedDishes.get(position);
            holder.nameTextView.setText(dish.getName());
            holder.priceTextView.setText(String.format("R$ %.2f", dish.getPrice()));
        } else {
            Drink drink = orderedDrinks.get(position - orderedDishes.size());
            holder.nameTextView.setText(drink.getName());
            holder.priceTextView.setText(String.format("R$ %.2f", drink.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return orderedDishes.size() + orderedDrinks.size();  // Soma os itens de pratos e bebidas
    }

    // ViewHolder para o item do carrinho
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;

        public CartViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.cart_item_name);
            priceTextView = itemView.findViewById(R.id.cart_item_price);
        }
    }
}
