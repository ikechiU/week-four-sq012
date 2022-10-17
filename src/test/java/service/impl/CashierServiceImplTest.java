package service.impl;

import model.Cart;
import model.Cashier;
import model.Customer;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;
import utils.CartList;
import utils.CartPriorityComparator;

import java.util.*;

import static org.junit.Assert.assertEquals;


class CashierServiceImplTest {
    CashierServiceImpl cashierService;
    Customer customer1;
    Customer customer2;
    Customer customer3;
    Cashier cashier;
    PriorityQueue<Cart> priorityQueue = new PriorityQueue<>(new CartPriorityComparator());
    Queue<Cart> normalQueue = new LinkedList<>();
    List<Cart> carts = new ArrayList<>();

    @BeforeEach
    public void init() {
        TestProductImplDB productImplDB = new TestProductImplDB();
        var storeProducts = new StoreDBImpl().getStoreProducts(productImplDB.getProductDB());
        cashierService = new CashierServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        CartServiceImpl cartService = new CartServiceImpl();
        cashier = new Cashier("Ikechi", "Male", 40, 23);

        customer1 = new Customer("Kendrick", "Female", 19, 200000.0);
        customerService.buy(customer1, "Rice", storeProducts, 2);
        customerService.buy(customer1, "Salmon", storeProducts, 7);
        customerService.buy(customer1, "Spaghetti", storeProducts, 1);
        Cart customer1Cart = cartService.createCart(customer1.getProductBoughtList(), customer1.getId(), customer1.getName());
        priorityQueue.add(customer1Cart);
        normalQueue.add(customer1Cart);

        customer2 = new Customer("Yinka", "Male", 29, 400000.0);
        customerService.buy(customer2, "Rice", storeProducts, 5);
        customerService.buy(customer2, "Beans", storeProducts, 8);
        customerService.buy(customer2, "Spaghetti", storeProducts, 1);
        customerService.buy(customer2, "Chicken", storeProducts, 2);
        customerService.buy(customer2, "Coke", storeProducts, 2);
        Cart customer2Cart = cartService.createCart(customer2.getProductBoughtList(), customer2.getId(), customer2.getName());
        priorityQueue.add(customer2Cart);
        normalQueue.add(customer2Cart);

        customer3 = new Customer("Michael", "Male", 35, 900000.0);
        customerService.buy(customer3, "Detergent", storeProducts, 1);
        customerService.buy(customer3, "Toothpaste", storeProducts, 1);
        customerService.buy(customer3, "Chips", storeProducts, 1);
        customerService.buy(customer3, "Grapes", storeProducts, 1);
        Cart customer3Cart = cartService.createCart(customer3.getProductBoughtList(), customer3.getId(), customer3.getName());
        priorityQueue.add(customer3Cart);
        normalQueue.add(customer3Cart);
        carts = new CartList().getCarts(customerService, cartService, storeProducts);
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
        Assertions.assertEquals("Henry", result.stream().toList().get(0));
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
        Assertions.assertEquals("Yinka", result.getCustomerName().stream().toList().get(1));
    }

    @Test
    void priorityQueueSize() {
        Assertions.assertEquals(3, priorityQueue.size());
    }

    @Test
    void normalQueueSize() {
        Assertions.assertEquals(3, priorityQueue.size());
    }

    @Test
    void priorityQueueSell() {
        var actual = cashierService.priorityQueueSell(cashier, priorityQueue);
        Assertions.assertEquals(priorityQueueResult(), actual);
    }

    @Test
    void normalQueueSell() {
        var actual = cashierService.normalQueueSell(cashier, normalQueue);
        Assertions.assertEquals(normalQueueResult(), actual);
    }

    @Test
    void getListOfProductBoughtTest() {
        var productBoughtList = customer1.getProductBoughtList();
        String actual = cashierService.getListOfProductBought(productBoughtList);
        String expected = "Rice, Salmon, Spaghetti";
        Assertions.assertEquals(expected, actual);
    }

    public String priorityQueueResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(mockReceipt("Yinka", "Rice, Beans, Spaghetti, Chicken, Coke",
                        "110984235", 18, 28900, "Ikechi")).append("\n")
                .append(mockReceipt("Kendrick", "Rice, Salmon, Spaghetti",
                        "110984236", 10, 19000, "Ikechi")).append("\n")
                .append(mockReceipt("Michael", "Detergent, Toothpaste, Chips, Grapes",
                        "110984237", 4, 8000, "Ikechi")).append("\n");
        return sb.toString();
    }

    public String normalQueueResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(mockReceipt("Kendrick", "Rice, Salmon, Spaghetti",
                        "120984235", 10, 19000, "Ikechi")).append("\n")
                .append(mockReceipt("Yinka", "Rice, Beans, Spaghetti, Chicken, Coke",
                        "120984236", 18, 28900, "Ikechi")).append("\n")
                .append(mockReceipt("Michael", "Detergent, Toothpaste, Chips, Grapes",
                        "120984237", 4, 8000, "Ikechi")).append("\n");
        return sb.toString();
    }

    public String mockReceipt(String customerName, String productBoughtList, String receipt, int totalQty, int totalAmount, String cashier) {
        return "Cashier{" +
                "message='" + " Thank you" + '\'' +
                ", customerName='" + customerName + '\'' +
                ", product='" + productBoughtList + '\'' +
                ", receipt='" + receipt + '\'' +
                ", totalProductQty='" + totalQty + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", cashier='" + cashier + '\'' +
                '}';
    }
}