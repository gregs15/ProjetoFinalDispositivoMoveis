package com.example.dispositivomovel.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.model.Category;
import com.example.dispositivomovel.model.Dish;

public class ItemMenuActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemDescriptionEditText;
    private EditText itemPriceEditText;
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_menu);

        itemNameEditText = findViewById(R.id.item_name);
        itemDescriptionEditText = findViewById(R.id.item_description);
        itemPriceEditText = findViewById(R.id.item_price);
        addItemButton = findViewById(R.id.add_item_button);

        addItemButton.setOnClickListener(v -> {
            String itemName = itemNameEditText.getText().toString();
            String itemDescription = itemDescriptionEditText.getText().toString();
            double itemPrice = Double.parseDouble(itemPriceEditText.getText().toString());

            Dish newDish = new Dish(0, itemName, itemDescription, itemPrice, new Category(1, "Categoria 1"), true);

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("newItem", newDish);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}

