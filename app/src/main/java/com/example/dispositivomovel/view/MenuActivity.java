package com.example.dispositivomovel.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.adapter.MenuAdapter;
import com.example.dispositivomovel.model.Category;
import com.example.dispositivomovel.model.Dish;
import com.example.dispositivomovel.model.Drink;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<Parcelable> menuItems = new ArrayList<>();
    private List<Dish> orderedDishes = new ArrayList<>();
    private List<Drink> orderedDrinks = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (savedInstanceState != null) {
            String json = savedInstanceState.getString("menuItems");
            Type type = new TypeToken<List<Parcelable>>() {}.getType();
            menuItems = gson.fromJson(json, type);
        }

        setupRecyclerView();
        loadMenuItems();
        setupAddItemButton();
        setupCartButton();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String json = gson.toJson(menuItems);
        outState.putString("menuItems", json);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MenuAdapter(menuItems, item -> {
            if (item instanceof Dish) {
                orderedDishes.add((Dish) item);
            } else if (item instanceof Drink) {
                orderedDrinks.add((Drink) item);
            }
            Toast.makeText(this, "Item adicionado ao carrinho", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupAddItemButton() {
        Button addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ItemMenuActivity.class);
            startActivity(intent); // Inicia a atividade sem esperar por um resultado
        });
    }

    private void setupCartButton() {
        Button cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            intent.putParcelableArrayListExtra("orderedDishes", new ArrayList<>(orderedDishes));
            intent.putParcelableArrayListExtra("orderedDrinks", new ArrayList<>(orderedDrinks));
            String json = gson.toJson(menuItems);
            intent.putExtra("menuItems", json);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String json = data.getStringExtra("menuItems");
            Type type = new TypeToken<List<Parcelable>>() {}.getType();
            menuItems = gson.fromJson(json, type);
            adapter.notifyDataSetChanged();
        }
    }

    private void loadMenuItems() {
        int categoryId = 1;

        menuItems.add(new Dish(1, "Prato 1", "Descrição do prato 1", 20.50, new Category(categoryId++, "Categoria 1"), true));
        menuItems.add(new Drink(2, "Bebida 1", "Descrição da bebida 1", 10.00, true, new Category(categoryId++, "Categoria 2")));
        menuItems.add(new Dish(3, "Prato 2", "Descrição do prato 2", 25.00, new Category(categoryId++, "Categoria 1"), true));
        menuItems.add(new Drink(4, "Bebida 2", "Descrição da bebida 2", 12.00, false, new Category(categoryId++, "Categoria 2")));

        adapter.notifyDataSetChanged();
    }
}
