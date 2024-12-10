package com.example.dispositivomovel.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<Dish> orderedDishes;
    private List<Drink> orderedDrinks;
    private double totalPrice;
    private String orderStatus;

    // Construtor
    public Order(int id, User user, List<Dish> orderedDishes, List<Drink> orderedDrinks, double totalPrice, String status) {
        this.id = id;
        this.user = user;
        this.orderedDishes = orderedDishes != null ? orderedDishes : new ArrayList<>();
        this.orderedDrinks = orderedDrinks != null ? orderedDrinks : new ArrayList<>();
        this.totalPrice = totalPrice;
        this.orderStatus = status;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
        updateTotalPrice();
    }

    public List<Drink> getOrderedDrinks() {
        return orderedDrinks;
    }

    public void setOrderedDrinks(List<Drink> orderedDrinks) {
        this.orderedDrinks = orderedDrinks;
        updateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Método para adicionar pratos e bebidas ao pedido e atualizar o total
    public void addDishToOrder(Dish dish) {
        if (dish != null) {
            this.orderedDishes.add(dish);
            updateTotalPrice();
        } else {
            System.out.println("Dish cannot be null");
        }
    }

    public void addDrinkToOrder(Drink drink) {
        if (drink != null) {
            this.orderedDrinks.add(drink);
            updateTotalPrice();
        } else {
            System.out.println("Drink cannot be null");
        }
    }

    // Método para calcular o preço total do pedido
    private void updateTotalPrice() {
        double total = 0.0;

        // Calculando o preço total dos pratos
        for (Dish dish : orderedDishes) {
            total += dish.getPrice();
        }

        // Calculando o preço total das bebidas
        for (Drink drink : orderedDrinks) {
            total += drink.getPrice();
        }

        this.totalPrice = total;
    }

    // Método para retornar um resumo da ordem
    public String getOrderSummary() {
        return "Pedido ID: " + id + "\n" +
                "Cliente: " + user.getName() + "\n" +
                "Total: R$ " + totalPrice + "\n" +
                "Status: " + orderStatus;
    }
}
