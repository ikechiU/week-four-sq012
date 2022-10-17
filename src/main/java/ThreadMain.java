import model.*;
import service.impl.*;
import utils.CartList;
import utils.Docs;

import java.io.File;
import java.util.*;

public class ThreadMain {
    private  static final Cashier cashier = new Cashier("Doris", "Female", 25, 2); //Polymorphism
    private  static final CashierServiceImpl cashierService = new CashierServiceImpl();
    private  static final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private  static final CartServiceImpl cart = new CartServiceImpl();
    private  static final StoreDBImpl storeDB = new StoreDBImpl();

    public static void main(String[] args) {
        File file = new File("src/main/resources/ProductsCSV.csv");
        var productList = Docs.readCSVUsingScanner(file);
        List<Product> products = Docs.getProducts(productList);
        storeActions(products);
    }

    private static void storeActions(List<Product> products) {
        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(products);
        Store storeProducts = storeDB.getStoreProducts(productsDB);

        List<Cart> carts = new CartList().getCarts(customerService, cart, storeProducts);

        var result = cashierService.asyncSell(cashier, carts);
        System.out.println(result);
    }

}
