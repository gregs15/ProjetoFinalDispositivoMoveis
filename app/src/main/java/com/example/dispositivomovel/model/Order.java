package com.example.dispositivomovel.model;

import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<Dish> orderedDishes;
    private List<Drink> orderedDrinks;
    private double totalPrice;
    private String orderStatus;

    public Order(int id, User user, List<Dish> orderedDishes, List<Drink> orderedDrinks, double totalPrice, String status) {
        this.id = id;
        this.user = user;
        this.orderedDishes = orderedDishes;
        this.orderedDrinks = orderedDrinks;
        this.totalPrice = totalPrice;
        this.orderStatus = status;
    }

    public List<Drink> getOrderedDrinks() {
        return orderedDrinks;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderedDishes(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public void setOrderedDrinks(List<Drink> orderedDrinks) {
        this.orderedDrinks = orderedDrinks;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
