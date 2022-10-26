package model;

import java.util.HashMap;

public class CustomerCart {
    private HashMap<String, Integer> cart;

    public CustomerCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }
}
