package com.example.dispositivomovel.model;

import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private int id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private boolean isAvailable;

    // Construtor
    public Dish(int id, String name, String description, double price, Category category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    // Métodos para acessar os campos (getters e setters)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Implementando o método describeContents
    @Override
    public int describeContents() {
        return 0;  // Não há arquivos ou outros tipos de objetos envolvidos
    }

    // Implementando o método writeToParcel para escrever os dados
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeParcelable(category, flags);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
    }

    // Construtor utilizado para ler os dados do Parcel
    protected Dish(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        category = (Category) in.readValue(Category.class.getClassLoader());
        isAvailable = in.readByte() != 0;
    }


    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };
}
