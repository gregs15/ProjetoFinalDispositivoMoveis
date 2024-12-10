package com.example.dispositivomovel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Drink implements Parcelable {
    private int id;
    private String name;
    private String description;
    private double price;
    private boolean isAlcoholic;
    private Category category;


    public Drink(int id, String name, String description, double price, boolean isAlcoholic, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAlcoholic = isAlcoholic;
        this.category = category;
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

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public Category getCategory() {
        return category;
    }

    // Implementando o método describeContents
    @Override
    public int describeContents() {
        return 0;
    }

    // Implementando o método writeToParcel para escrever os dados
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeByte((byte) (isAlcoholic ? 1 : 0));
        dest.writeParcelable(category, flags);
    }


    protected Drink(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        isAlcoholic = in.readByte() != 0;
        category = (Category) in.readValue(Category.class.getClassLoader());
    }


    public static final Parcelable.Creator<Drink> CREATOR = new Parcelable.Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
}
