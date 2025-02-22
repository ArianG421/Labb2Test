package com.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Item, Integer> items = new HashMap<>();

    public void addItem(Item item, int quantity) {
        items.put(item, quantity);
    }

    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getTotalPrice() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void applyDiscount(double discountRate) {
        double totalPrice = getTotalPrice();
        double discountedPrice = totalPrice * (1 - discountRate);
        items.put(new Item("Discount", -totalPrice + discountedPrice), 1);
    }

    public void updateQuantity(Item item, int quantity) {
        items.put(item, quantity);
    }

}

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


}