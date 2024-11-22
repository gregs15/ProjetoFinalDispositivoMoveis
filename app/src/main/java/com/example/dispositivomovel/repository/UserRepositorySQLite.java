package com.example.dispositivomovel.repository;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.dispositivomovel.database.DbManager;
import com.example.dispositivomovel.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositorySQLite implements UserRepository {
    private DbManager dbManager;

    public UserRepositorySQLite(Context context) {
        dbManager = new DbManager(context);
    }

    @Override
    public void addUser(User user) {
        dbManager.insertUser(user.getName(), user.getEmail(), user.getAddress(), user.getPhone(), user.getPassword());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = dbManager.getAllUsers();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    users.add(cursorToUser(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("UserRepositorySQLite", "Erro ao buscar usuários: ", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        Cursor cursor = null;
        try {
            cursor = dbManager.getUserById(id);
            if (cursor != null && cursor.moveToFirst()) {
                return cursorToUser(cursor);
            }
        } catch (Exception e) {
            Log.e("UserRepositorySQLite", "Erro ao buscar usuário por ID: ", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
       dbManager.updateUser(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getPhone(), user.getPassword());
    }

    @Override
    public void deleteUser(int id) {
        dbManager.deleteUser(id);
    }

    @Override
    public User authenticateUser(String username, String password) {

        Cursor cursor = null;
        try {
            cursor = dbManager.getUserByUsername(username);
            if (cursor != null && cursor.moveToFirst()) {

                User user = cursorToUser(cursor);

                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            Log.e("UserRepositorySQLite", "Erro na autenticação do usuário: ", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    // Método  para converter o Cursor em um objeto User
    private User cursorToUser(Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.COLUMN_USER_NAME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.COLUMN_USER_EMAIL));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.COLUMN_USER_ADDRESS));
        String phone = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.COLUMN_USER_PHONE));
        String password = cursor.getString(cursor.getColumnIndexOrThrow(DbManager.COLUMN_USER_PASSWORD)); // Recuperando a senha
        return new User(name, email, address, phone, password);
    }
}

