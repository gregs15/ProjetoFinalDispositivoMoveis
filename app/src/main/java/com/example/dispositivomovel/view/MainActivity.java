package com.example.dispositivomovel.view;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.dispositivomovel.R;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        executor = ContextCompat.getMainExecutor(this);

        // Instanciar o BiometricPrompt com uma AuthenticationCallback
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Erro na autenticação: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Autenticação bem-sucedida!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Se o PIN ou biometria falhar, exibe a mensagem do erro.
                Toast.makeText(getApplicationContext(),
                        "Falha na autenticação. Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Entrar com PIN")
                .setSubtitle("Digite seu PIN ou use a biometria")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL) // Permite usar biometria ou credenciais do dispositivo
                .build();


        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                // A biometria está disponível e configurada
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Este dispositivo não possui hardware biométrico.", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "O hardware biométrico está temporariamente indisponível.", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Se o usuário não tiver biometria configurada, você pode exibir uma mensagem.
                Toast.makeText(this, "Nenhuma biometria configurada. Por favor, configure a biometria nas configurações do dispositivo.", Toast.LENGTH_SHORT).show();
                break;
        }


        Button btnPin = findViewById(R.id.btnPin);
        btnPin.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });


        Button btnLoginNormal = findViewById(R.id.btnLoginNormal);
        btnLoginNormal.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}


