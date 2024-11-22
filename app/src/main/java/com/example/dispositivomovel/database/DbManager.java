package com.example.dispositivomovel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_cardapio.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_ADDRESS = "address";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_PASSWORD = "password";

    // Modificação na criação da tabela para incluir o campo de senha
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " ("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT NOT NULL, "
            + COLUMN_USER_EMAIL + " TEXT NOT NULL, "
            + COLUMN_USER_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_USER_PHONE + " TEXT NOT NULL, "
            + COLUMN_USER_PASSWORD + " TEXT NOT NULL"
            + ");";

    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("ALTER TABLE " + TABLE_USER + " ADD COLUMN " + COLUMN_USER_PASSWORD + " TEXT NOT NULL DEFAULT '';");
    }


    public long insertUser(String name, String email, String address, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_PASSWORD, password);

        long newRowId = db.insert(TABLE_USER, null, values);
        db.close();
        return newRowId;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USER, null, null, null, null, null, null);
    }

    public Cursor getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        return db.query(TABLE_USER, null, selection, selectionArgs, null, null, null);
    }

    public int updateUser(int userId, String name, String email, String address, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_PASSWORD, password);

        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int rowsUpdated = db.update(TABLE_USER, values, selection, selectionArgs);
        db.close();
        return rowsUpdated;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int rowsDeleted = db.delete(TABLE_USER, selection, selectionArgs);
        db.close();
        return rowsDeleted;
    }

    // Método para autenticar o usuário com nome de usuário e senha
    public Cursor getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {username};
        return db.query(TABLE_USER, null, selection, selectionArgs, null, null, null);
    }

}
