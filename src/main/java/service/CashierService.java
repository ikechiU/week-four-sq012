package service;

import model.*;

import java.util.List;
import java.util.PriorityQueue;

public interface CashierService {
    String priorityQueueSell(Cashier cashier, PriorityQueue<Cart> queue);
    String normalQueueSell(Cashier cashier, PriorityQueue<Cart> queue);
    String getListOfProductBought(List<ProductBought> productBoughtList);
    String receipt(String customerName, List<ProductBought> productBoughtList, String receipt, int totalQty, int totalAmount, String cashier);
}
