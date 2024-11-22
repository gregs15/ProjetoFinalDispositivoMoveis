package com.example.dispositivomovel.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<Object> menuItems = new ArrayList<>();
    private List<Object> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MenuAdapter(menuItems, item -> {
            cartItems.add(item); // Adiciona o item ao carrinho
            Toast.makeText(this, "Item adicionado ao carrinho", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        Button cartButton = findViewById(R.id.cart_button);
//        cartButton.setOnClickListener(v -> {
//            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
//            intent.putParcelableArrayListExtra("cartItems", new ArrayList<>(cartItems));
//            startActivity(intent);
//        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}