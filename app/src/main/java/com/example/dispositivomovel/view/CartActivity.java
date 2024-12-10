package com.example.dispositivomovel.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.adapter.CartAdapter;
import com.example.dispositivomovel.model.Dish;
import com.example.dispositivomovel.model.Drink;
import com.example.dispositivomovel.model.Order;
import com.example.dispositivomovel.model.User;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Dish> orderedDishes = new ArrayList<>();
    private List<Drink> orderedDrinks = new ArrayList<>();
    private TextView totalPriceTextView;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_cart);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmButton = findViewById(R.id.confirm_order_button);

        // Recupera os itens do carrinho (pratos e bebidas)
        Intent intent = getIntent();
        if (intent != null) {
            orderedDishes = ((Intent) intent).getParcelableArrayListExtra("orderedDishes");
            orderedDrinks = intent.getParcelableArrayListExtra("orderedDrinks");
        }

        // Configura o RecyclerView (aqui você precisaria adaptar o adapter para tratar pratos e bebidas)
        cartAdapter = new CartAdapter(orderedDishes, orderedDrinks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        // Calcula o total
        double total = calculateTotal(orderedDishes, orderedDrinks);
        totalPriceTextView.setText(String.format("Total: R$ %.2f", total));

        // Botão de confirmação do pedido
        confirmButton.setOnClickListener(v -> {
            // Cria o pedido com os itens do carrinho
            Order order = new Order(1, new User("João", "joao@example.com", "Rua X, 123", "123456789", "senha123"), orderedDishes, orderedDrinks, total, "Em andamento");

            // Processar o pedido, salvar no banco ou outro processo
            // Aqui você pode salvar o pedido ou mostrar um resumo

            finish(); // Finaliza a Activity de Carrinho
        });
    }

    private double calculateTotal(List<Dish> orderedDishes, List<Drink> orderedDrinks) {
        double total = 0.0;
        // Calcula o preço total dos pratos
        for (Dish dish : orderedDishes) {
            total += dish.getPrice();
        }

        // Calcula o preço total das bebidas
        for (Drink drink : orderedDrinks) {
            total += drink.getPrice();
        }
        return total;
    }
}

