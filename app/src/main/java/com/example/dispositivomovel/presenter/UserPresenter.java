package com.example.dispositivomovel.presenter;

import com.example.dispositivomovel.model.User;
import com.example.dispositivomovel.repository.UserRepository;
import com.example.dispositivomovel.repository.UserRepositorySQLite;

public class UserPresenter implements UserPresenterContract.Presenter {

    private UserRepository userRepository;
    private UserPresenterContract.View view;

    public UserPresenter(UserPresenterContract.View view) {
        this.view = view;
        this.userRepository = new UserRepositorySQLite(view.getContext()); // Inicializa o repositório com SQLite
    }

    @Override
    public void onRegisterUser(User user) {
        // Adiciona o usuário no banco de dados
        userRepository.addUser(user);
        view.showMessage("Usuário cadastrado com sucesso!");
    }

    @Override
    public void onLogin(String username, String password) {
        // Autentica o usuário
        User user = userRepository.authenticateUser(username, password);

        if (user != null) {
            view.showMessage("Login bem-sucedido!");
            view.navigateToMenu();
        } else {
            view.showMessage("Usuário ou senha inválidos!");
        }
    }
}
