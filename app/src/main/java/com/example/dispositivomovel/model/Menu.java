package com.example.dispositivomovel.model;

import java.util.List;

public class Menu {
    private int id;
    private String title;
    private List<Dish> dishes;
    private List<Drink> drinks;

    public Menu(int id, String title, List<Dish> dishes, List<Drink> drinks) {
        this.id = id;
        this.title = title;
        this.dishes = dishes;
        this.drinks = drinks;
    }

    //metodos getters e setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

}
