package service;

import model.*;

import java.util.*;

public interface CashierService {
    Integer asyncGetQuantitiesOfOneProductSold(Cashier cashier, List<Cart> carts, String specificProductName);
    Integer asyncGetQuantitiesSold(Cashier cashier, List<Cart> carts);
    Integer asyncGetAmountSold(Cashier cashier, List<Cart> carts);
    Collection<String> asyncGetCustomerNames(Cashier cashier, List<Cart> carts);
    Set<String> asyncGetProductsSold(Cashier cashier, List<Cart> carts);
    Map<String, Integer> asyncGetMapOfProductsSold(Cashier cashier, List<Cart> carts);
    List<Customer> newAsyncSellThread(Cashier cashier, List<Customer> customers, Store storeProducts);
    List<Customer> newNonAsyncSellThread(Cashier cashier, List<Customer> customers, Store storeProducts);
    Sales asyncSell(Cashier cashier, List<Cart> carts);
    Sales nonAsyncSell(Cashier cashier, List<Cart> carts);
    Sales nonAsyncExecutorsSell(Cashier cashier, List<Cart> carts);
    String priorityQueueSell(Cashier cashier, Queue<Cart> queue);
    String normalQueueSell(Cashier cashier, Queue<Cart> queue);
    String getListOfProductBought(List<ProductBought> productBoughtList);
    String receipt(String customerName, List<ProductBought> productBoughtList, String receipt, int totalQty, int totalAmount, String cashier);
}
