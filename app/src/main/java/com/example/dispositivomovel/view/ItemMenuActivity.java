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


        itemNameText = findViewById(R.id.item_name);
        itemDescriptionText = findViewById(R.id.item_description);
        itemPriceText = findViewById(R.id.item_price);
        selectItemButton = findViewById(R.id.select_item_button);


        String itemName = "Prato Exemplo";
        String itemDescription = "Descrição do prato";
        double itemPrice = 25.00;

        itemNameText.setText(itemName);
        itemDescriptionText.setText(itemDescription);
        itemPriceText.setText(String.format("R$ %.2f", itemPrice));


        selectItemButton.setOnClickListener(v -> {

            Intent resultIntent = new Intent();
            resultIntent.putExtra("item_name", itemName);
            resultIntent.putExtra("item_price", itemPrice);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
