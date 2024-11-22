package com.example.dispositivomovel.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dispositivomovel.R;
import com.example.dispositivomovel.model.User;
import com.example.dispositivomovel.presenter.UserPresenter;
import com.example.dispositivomovel.presenter.UserPresenterContract;

public class RegisterActivity extends AppCompatActivity implements UserPresenterContract.View {

    private EditText editTextName, editTextEmail, editTextPassword,editTextAddress,editTextPhone;
    private Button buttonRegister;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        editTextName = this.findViewById(R.id.editTextName);
        editTextEmail = this.findViewById(R.id.editTextEmail);
        editTextPassword = this.findViewById(R.id.editTextPassword);
        editTextAddress = this.findViewById(R.id.editTextAddress);
        editTextPhone = this.findViewById(R.id.editTextPhone);

        buttonRegister = this.findViewById(R.id.buttonRegister);

        userPresenter = new UserPresenter(this);

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String address = editTextAddress.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                showMessage("Por favor, preencha todos os campos.");
            } else {
                User user = new User(name, email, password, address, phone);
                userPresenter.onRegisterUser(user);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMenu() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}

