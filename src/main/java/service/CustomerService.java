package service;

import model.Customer;
import model.Product;
import model.Store;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {
    String oldBuy(Customer customer, String productName, Store storeProducts, int quantity); //Abstraction
    String oldBuy(Customer customer, HashMap<String, Integer> productNames, Store storeProducts); //Abstraction
    String oldBuy(Customer customer, String productName, int year, Store storeProducts, int quantity);
    List<Product> buyAsyncThread(List<Customer> customers, Store storeProducts);
    List<Product> buyNonAsyncThread(List<Customer> customers, Store storeProducts);
    List<Customer> newBuyAsyncThread(List<Customer> customers, Store storeProducts);
    List<Customer> newBuyNonAsyncThread(List<Customer> customers, Store storeProducts);
    Product getProduct(Store storeProducts, String productName);
    int extractYear(String year);
    String confirmProductName(String productName);
}
