package service;

import model.Cashier;
import model.Store;
import model.Product;

public interface CashierService {
    String sell(Cashier cashier, String productName); //Abstraction
}
