package com.example.dispositivomovel.repository;

import com.example.dispositivomovel.model.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void updateUser(User user);
    void deleteUser(int id);

    User authenticateUser(String username, String password);
}
