package service;

import model.Customer;
import model.Product;
import model.Store;

public interface CustomerService {
    String buy(Customer customer, String productName, Store storeProducts); //Abstraction
    String buy(Customer customer, String productName, int year, Store storeProducts); //Method overloading
    Product getProduct(Store storeProducts, String productName);
    int extractYear(String year);
    String confirmProductName(String productName);
}
