package com.example.dispositivomovel.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dispositivomovel.R;

public class ItemMenuActivity extends AppCompatActivity {

    private Button selectItemButton;
    private TextView itemNameText, itemDescriptionText, itemPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_menu);

        // Acessa os componentes do layout
        itemNameText = findViewById(R.id.item_name);
        itemDescriptionText = findViewById(R.id.item_description);
        itemPriceText = findViewById(R.id.item_price);
        selectItemButton = findViewById(R.id.select_item_button);

        // Preencher os dados do item com base em sua escolha
        String itemName = "Prato Exemplo";  // Aqui você pode passar o item selecionado
        String itemDescription = "Descrição do prato";
        double itemPrice = 25.00;

        itemNameText.setText(itemName);
        itemDescriptionText.setText(itemDescription);
        itemPriceText.setText(String.format("R$ %.2f", itemPrice));

        // Configura o botão de "Adicionar ao Carrinho"
        selectItemButton.setOnClickListener(v -> {
            // Envia os dados de volta para a MenuActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("item_name", itemName);  // Passa o nome do item
            resultIntent.putExtra("item_price", itemPrice); // Passa o preço do item

            setResult(RESULT_OK, resultIntent);
            finish();  // Fecha a ItemMenuActivity e retorna para a MenuActivity
        });
    }
}
