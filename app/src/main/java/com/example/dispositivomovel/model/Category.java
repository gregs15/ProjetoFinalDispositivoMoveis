package com.example.dispositivomovel.model;

import android.os.Parcel;
import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int id;
    private String name;

    // Construtor normal
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Implementação de describeContents
    @Override
    public int describeContents() {
        return 0; // Não há necessidade de descrever conteúdos adicionais
    }

    // Escreve os dados no Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);      // Escreve o id no Parcel
        dest.writeString(name); // Escreve o nome no Parcel
    }

    // Construtor que cria a instância de Category a partir do Parcel
    public Category(Parcel in) {
        id = in.readInt();      // Lê o id do Parcel
        name = in.readString(); // Lê o nome do Parcel
    }

    // Criador (Creator) para criar objetos Category a partir do Parcel
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in); // Cria a instância de Category com os dados do Parcel
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size]; // Cria um array de Category
        }
    };
}

