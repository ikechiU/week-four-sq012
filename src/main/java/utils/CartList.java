package utils;

import model.Cart;
import model.Customer;
import model.Store;
import service.impl.CartServiceImpl;
import service.impl.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartList {
    private final Customer customer = new Customer("Henry", "Male", 43, 2111800.0);
    private final Customer customer1 = new Customer("Yinka", "Male", 43, 250000.0);
    private final Customer customer2 = new Customer("Agnes", "Female", 20, 2500000.0);
    private final Customer customer3 = new Customer("Agatha", "Female", 30, 110500.0);
    private final Customer customer4 = new Customer("Ben", "Male", 31, 905000.0);
    private final Customer customer5 = new Customer("Jim", "Male", 32, 900000.0);
    private final Customer customer6 = new Customer("Simi", "Female", 32, 800000.0);
    private final Customer customer7 = new Customer("Anne", "Female", 39, 850000.0);
    private final Customer customer8 = new Customer("Sam", "Male", 79, 550000.0);
    private final Customer customer9 = new Customer("Nwachukwu", "Male", 89, 650000.0);
    private final Customer customer10 = new Customer("Joy", "Female", 49, 750000.0);

    public List<Cart> getCarts(CustomerServiceImpl customerService, CartServiceImpl cart, Store storeProducts) {
        HashMap<String, Integer> customerProductList = new HashMap<>();
        customerProductList.put("Rice", 10);
        customerProductList.put("Beans", 4);
        customerProductList.put("Yogurt", 2);
        customerProductList.put("Coke", 5);

        HashMap<String, Integer> customerProductList1 = new HashMap<>();
        customerProductList1.put("Rice", 2);
        customerProductList1.put("Beans", 2);
        customerProductList1.put("Pasta", 2);
        customerProductList1.put("Jam", 2);

        HashMap<String, Integer> customerProductList2 = new HashMap<>();
        customerProductList2.put("Rice", 1);
        customerProductList2.put("Beans", 1);
        customerProductList2.put("Pasta", 1);
        customerProductList2.put("Jam", 1);

        HashMap<String, Integer> customerProductList3 = new HashMap<>();
        customerProductList3.put("Coffee", 1);
        customerProductList3.put("Eggs", 3);
        customerProductList3.put("Pasta", 2);
        customerProductList3.put("Jam", 2);

        HashMap<String, Integer> customerProductList4 = new HashMap<>();
        customerProductList4.put("Cheese", 2);
        customerProductList4.put("Tea", 2);

        HashMap<String, Integer> customerProductList5 = new HashMap<>();
        customerProductList5.put("Heineken", 5);
        customerProductList5.put("Coffee", 2);

        HashMap<String, Integer> customerProductList6 = new HashMap<>();
        customerProductList6.put("Milk", 5);
        customerProductList6.put("Apples", 2);

        HashMap<String, Integer> customerProductList7 = new HashMap<>();
        customerProductList7.put("Spaghetti", 2);
        customerProductList7.put("Heineken", 2);

        HashMap<String, Integer> customerProductList8 = new HashMap<>();
        customerProductList8.put("Cucumber", 2);
        customerProductList8.put("Peppers", 3);

        HashMap<String, Integer> customerProductList9 = new HashMap<>();
        customerProductList9.put("Mandarins", 4);
        customerProductList9.put("Mango", 1);

        HashMap<String, Integer> customerProductList10 = new HashMap<>();
        customerProductList10.put("Strawberries", 3);
        customerProductList10.put("Blueberries", 10);

        customerService.oldBuy(customer, customerProductList, storeProducts);
        customerService.oldBuy(customer1, customerProductList1, storeProducts);
        customerService.oldBuy(customer2, customerProductList2, storeProducts);
        customerService.oldBuy(customer3, customerProductList3, storeProducts);
        customerService.oldBuy(customer4, customerProductList4, storeProducts);
        customerService.oldBuy(customer5, customerProductList5, storeProducts);
        customerService.oldBuy(customer6, customerProductList6, storeProducts);
        customerService.oldBuy(customer7, customerProductList7, storeProducts);
        customerService.oldBuy(customer8, customerProductList8, storeProducts);
        customerService.oldBuy(customer9, customerProductList9, storeProducts);
        customerService.oldBuy(customer10, customerProductList10, storeProducts);

        Cart customerCart = cart.createCart(customer.getProductBoughtList(), customer.getId(), customer.getName());
        Cart customerCart1 = cart.createCart(customer1.getProductBoughtList(), customer1.getId(), customer1.getName());
        Cart customerCart2 = cart.createCart(customer2.getProductBoughtList(), customer2.getId(), customer2.getName());
        Cart customerCart3 = cart.createCart(customer3.getProductBoughtList(), customer3.getId(), customer3.getName());
        Cart customerCart4 = cart.createCart(customer4.getProductBoughtList(), customer4.getId(), customer4.getName());
        Cart customerCart5 = cart.createCart(customer5.getProductBoughtList(), customer5.getId(), customer5.getName());
        Cart customerCart6 = cart.createCart(customer6.getProductBoughtList(), customer6.getId(), customer6.getName());
        Cart customerCart7 = cart.createCart(customer7.getProductBoughtList(), customer7.getId(), customer7.getName());
        Cart customerCart8 = cart.createCart(customer8.getProductBoughtList(), customer8.getId(), customer8.getName());
        Cart customerCart9 = cart.createCart(customer9.getProductBoughtList(), customer9.getId(), customer9.getName());
        Cart customerCart10 = cart.createCart(customer10.getProductBoughtList(), customer10.getId(), customer10.getName());

        List<Cart> carts = new ArrayList<>();
        carts.add(customerCart);
        carts.add(customerCart1);
        carts.add(customerCart2);
        carts.add(customerCart3);
        carts.add(customerCart4);
        carts.add(customerCart5);
        carts.add(customerCart6);
        carts.add(customerCart7);
        carts.add(customerCart8);
        carts.add(customerCart9);
        carts.add(customerCart10);

        return carts;
    }
}
