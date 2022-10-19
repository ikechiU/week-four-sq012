package service.impl;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartServiceImplTest {
    CartServiceImpl cartService;
    CustomerServiceImpl customerService;
    Customer customer;

    @BeforeEach
    void init() {
        TestProductImplDB productImplDB = new TestProductImplDB();
        Store storeProducts = new StoreDBImpl().getStoreProducts(productImplDB.getProductDB());
        cartService = new CartServiceImpl();
        customerService = new CustomerServiceImpl();
        customer = new Customer("Henry", "Male", 43, 20000.0);
        customerService.buy(customer, "Rice", storeProducts, 2);
        customerService.buy(customer, "Beans", storeProducts, 2);
        customerService.buy(customer, "Chicken", storeProducts, 2);
    }


    @Test
    void createCart() {
        Cart cart = cartService.createCart(customer.getProductBoughtList(), customer.getId(), customer.getName());
        assertEquals("Henry", cart.getCustomerName());
        assertNotNull(cart);
        assertEquals(3, cart.getProductBoughtList().size());
    }

    @Test
    void getTotalQtyOfProductBought() {
        assertEquals(6, cartService.getTotalQtyOfProductBought(customer.getProductBoughtList()));
    }

    @Test
    void getTotalAmountOfProductBought() {
        assertEquals(10600, cartService.getTotalAmountOfProductBought(customer.getProductBoughtList()));
    }
}