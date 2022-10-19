package service.impl;

import model.Cart;
import model.Cashier;
import model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;
import utils.CartList;
import utils.CartPriorityComparator;

import java.util.*;

class CashierServiceImplThreadTest {
    CashierServiceImpl cashierService;
    List<Cart> carts;
    Cashier cashier;

    @BeforeEach
    public void init() {
        cashierService = new CashierServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        CartServiceImpl cartService = new CartServiceImpl();
        TestProductImplDB productImplDB = new TestProductImplDB();
        var storeProducts = new StoreDBImpl().getStoreProducts(productImplDB.getProductDB());
        carts = new CartList().getCarts(customerService, cartService, storeProducts);
        cashier = new Cashier("Ikechi", "Male", 40, 23);
    }

    @Test
    void asyncGetQuantitiesOfOneProductSold() {
        var result = cashierService.asyncGetQuantitiesOfOneProductSold(cashier, carts, "Rice");
        Assertions.assertEquals(Integer.valueOf(13), result);
    }

    @Test
    void asyncGetQuantitiesSold() {
        var result = cashierService.asyncGetQuantitiesSold(cashier, carts);
        Assertions.assertEquals(Integer.valueOf(86), result);
    }

    @Test
    void asyncGetAmountSold() {
        var result = cashierService.asyncGetAmountSold(cashier, carts);
        Assertions.assertEquals(Integer.valueOf(160_600), result);
    }

    @Test
    void asyncGetCustomerNames() {
        var result = cashierService.asyncGetCustomerNames(cashier, carts);
        Assertions.assertEquals("Henry", result.toArray()[0]);
        Assertions.assertEquals(11, result.size());
    }

    @Test
    void asyncGetProductsSold() {
        var result = cashierService.asyncGetProductsSold(cashier, carts);
        Assertions.assertEquals(20, result.size());
        Assertions.assertTrue(result.contains("Coke"));
        Assertions.assertFalse(result.contains("Chicken"));
    }

    @Test
    void asyncGetMapOfProductsSold() {
        var result = cashierService.asyncGetMapOfProductsSold(cashier, carts);
        Assertions.assertEquals(7, result.get("Heineken"));
        Assertions.assertEquals(20, result.size());
        Assertions.assertEquals(5, result.get("Jam"));
    }

    @Test
    void asyncSell() {
        var result = cashierService.asyncSell(cashier, carts);
        System.out.println(result);
        Assertions.assertEquals(160600, result.getAmountSold());
        Assertions.assertEquals(20, result.getProductsSold().size());
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Heineken"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Coke"));
        Assertions.assertEquals(1, result.getMapProductQtySold().get("Mango"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Yogurt"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cheese"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Coffee"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Eggs"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Apples"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Tea"));
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Beans"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Strawberries"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Spaghetti"));
        Assertions.assertEquals(4, result.getMapProductQtySold().get("Mandarins"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Jam"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Pasta"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Peppers"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cucumber"));
        Assertions.assertEquals(10, result.getMapProductQtySold().get("Blueberries"));
        Assertions.assertEquals(13, result.getMapProductQtySold().get("Rice"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(11, result.getCustomerName().size());
    }

    @Test
    void nonAsyncSell() {
        var result = cashierService.nonAsyncSell(cashier, carts);
        System.out.println(result);
        Assertions.assertEquals(160600, result.getAmountSold());
        Assertions.assertEquals(20, result.getProductsSold().size());
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Heineken"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Coke"));
        Assertions.assertEquals(1, result.getMapProductQtySold().get("Mango"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Yogurt"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cheese"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Coffee"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Eggs"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Apples"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Tea"));
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Beans"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Strawberries"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Spaghetti"));
        Assertions.assertEquals(4, result.getMapProductQtySold().get("Mandarins"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Jam"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Pasta"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Peppers"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cucumber"));
        Assertions.assertEquals(10, result.getMapProductQtySold().get("Blueberries"));
        Assertions.assertEquals(13, result.getMapProductQtySold().get("Rice"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(11, result.getCustomerName().size());
    }

    @Test
    void nonAsyncExecutorsSellTest() {
        var result = cashierService.nonAsyncExecutorsSell(cashier, carts);
        System.out.println(result);
        Assertions.assertEquals(160600, result.getAmountSold());
        Assertions.assertEquals(20, result.getProductsSold().size());
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Heineken"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Coke"));
        Assertions.assertEquals(1, result.getMapProductQtySold().get("Mango"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Yogurt"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cheese"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Coffee"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Eggs"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Apples"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Tea"));
        Assertions.assertEquals(7, result.getMapProductQtySold().get("Beans"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Strawberries"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Spaghetti"));
        Assertions.assertEquals(4, result.getMapProductQtySold().get("Mandarins"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Jam"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Pasta"));
        Assertions.assertEquals(3, result.getMapProductQtySold().get("Peppers"));
        Assertions.assertEquals(2, result.getMapProductQtySold().get("Cucumber"));
        Assertions.assertEquals(10, result.getMapProductQtySold().get("Blueberries"));
        Assertions.assertEquals(13, result.getMapProductQtySold().get("Rice"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(5, result.getMapProductQtySold().get("Milk"));
        Assertions.assertEquals(11, result.getCustomerName().size());
    }
}