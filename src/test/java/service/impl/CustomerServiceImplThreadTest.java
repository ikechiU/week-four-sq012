package service.impl;

import model.Customer;
import model.Product;
import model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;
import utils.CustomersList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplThreadTest {
    private Store storeProducts;
    CustomerServiceImpl customerService;
    List<Customer> customers;

    @BeforeEach
    void init() {
        TestProductImplDB productImplDB = new TestProductImplDB();
        storeProducts = new StoreDBImpl().getStoreProducts(productImplDB.getProductDB());
        customerService = new CustomerServiceImpl();
        customers = new CustomersList().getCustomers();
    }

    @Test
    void buyAsyncThread() {
        var result = customerService.buyAsyncThread(customers, storeProducts);
        for(Product product : result) {
            System.out.println(product);
        }

        assertEquals(20, storeProducts.getProducts().get(18).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(20).getQuantity());
        assertEquals(0, storeProducts.getProducts().get(22).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(23).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(27).getQuantity());
        assertEquals(1, storeProducts.getProducts().get(47).getQuantity());
    }

    @Test
    void buyNonAsyncThread() {
        var result = customerService.buyNonAsyncThread(customers, storeProducts);
        for(Product product : result) {
            System.out.println(product);
        }
        assertEquals(20, storeProducts.getProducts().get(18).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(20).getQuantity());
        assertEquals(0, storeProducts.getProducts().get(22).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(23).getQuantity());
        assertEquals(18, storeProducts.getProducts().get(27).getQuantity());
        assertEquals(1, storeProducts.getProducts().get(47).getQuantity());
    }

    @Test
    void newBuyAsyncThread() {
        var result = customerService.newBuyAsyncThread(customers, storeProducts);
        for(Customer customer : result) {
            System.out.println(customer);
        }

        assertEquals("4", customers.get(0).getCartStatus().get("Beans"));
        assertEquals("Product not available.", customers.get(0).getCartStatus().get("Suya"));
        assertEquals("Insufficient balance.", customers.get(0).getCartStatus().get("Rice"));

        assertEquals("2", customers.get(3).getCartStatus().get("Jam"));
        assertEquals("2", customers.get(3).getCartStatus().get("Pasta"));
        assertEquals("10", customers.get(3).getCartStatus().get("Coffee"));
        assertNull(customers.get(3).getCartStatus().get("Beans"));
        assertEquals("Change quantity, only 20 available in store.", customers.get(3).getCartStatus().get("Eggs"));

        var jamPrice = 2000 * 2;
        var pastaPrice = 2000 * 2;
        var coffeePrice = 2000 * 10;
        var customer3WalletBalance = 110500.0 - jamPrice - pastaPrice - coffeePrice;
        assertEquals(customer3WalletBalance, customers.get(3).getWalletBalance());
    }

    @Test
    void newBuyNonAsyncThread() {
        var result = customerService.newBuyNonAsyncThread(customers, storeProducts);
        for(Customer customer : result) {
            System.out.println(customer);
        }

        assertEquals("4", customers.get(0).getCartStatus().get("Beans"));
        assertEquals("Product not available.", customers.get(0).getCartStatus().get("Suya"));
        assertEquals("Insufficient balance.", customers.get(0).getCartStatus().get("Rice"));

        assertEquals("2", customers.get(3).getCartStatus().get("Jam"));
        assertEquals("2", customers.get(3).getCartStatus().get("Pasta"));
        assertEquals("10", customers.get(3).getCartStatus().get("Coffee"));
        assertNull(customers.get(3).getCartStatus().get("Beans"));
        assertEquals("Change quantity, only 20 available in store.", customers.get(3).getCartStatus().get("Eggs"));

        var jamPrice = 2000 * 2;
        var pastaPrice = 2000 * 2;
        var coffeePrice = 2000 * 10;
        var customer3WalletBalance = 110500.0 - jamPrice - pastaPrice - coffeePrice;
        assertEquals(customer3WalletBalance, customers.get(3).getWalletBalance());
    }


}