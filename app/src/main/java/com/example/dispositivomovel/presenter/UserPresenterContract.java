package com.example.dispositivomovel.presenter;

import android.content.Context;

import com.example.dispositivomovel.model.User;

public interface UserPresenterContract {
    interface View {
        void showMessage(String message);
        void navigateToMenu();
        Context getContext();
    }

    interface Presenter {
        void onRegisterUser(User user);
        void onLogin(String username, String password);
    }
}

