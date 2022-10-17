package service;

import model.Customer;
import model.Product;
import model.Store;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {
    String buy(Customer customer, String productName, Store storeProducts, int quantity); //Abstraction
    String buy(Customer customer, HashMap<String, Integer> productNames, Store storeProducts); //Abstraction
    String buy(Customer customer, String productName, int year, Store storeProducts, int quantity); //Method overloading
    Product getProduct(Store storeProducts, String productName);
    int extractYear(String year);
    String confirmProductName(String productName);
}
