package service.impl;

import model.*;
import service.CashierService;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CashierServiceImpl implements CashierService {

    private static final int priorityReceipt = 110984235;
    private static final int normalQueueReceipt = 120984235;

    @Override
    public String priorityQueueSell(Cashier cashier, Queue<Cart> queue) {
        return getQueueResult(cashier, queue);
    }

    @Override
    public String normalQueueSell(Cashier cashier, Queue<Cart> queue) {
        return getQueueResult(cashier, queue);
    }

    private String getQueueResult(Cashier cashier, Queue<Cart> queue) {
        if (queue.isEmpty()) return "Queue is empty";
        int count = 0;

        String cashierName = cashier.getName();
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            var cart = queue.peek();
            if (cart != null) {
                var customerName = cart.getCustomerName();
                var product = cart.getProductBoughtList();
                var totalQty = cart.getTotalQty();
                var totalAmount = cart.getTotalAmount();
                sb.append(receipt(customerName, product, String.valueOf(getQueueType(queue) + count), totalQty, totalAmount, cashierName)).append("\n");
                System.out.println(receipt(customerName, product, String.valueOf(priorityReceipt + count), totalQty, totalAmount, cashierName));
            }
            queue.poll();
            count++;
        }
        return sb.toString();
    }

    private int getQueueType(Queue<Cart> queue) {
        if(queue.getClass().equals(PriorityQueue.class)) return priorityReceipt;
        else return normalQueueReceipt;
    }

    @Override
    public String receipt(String customerName, List<ProductBought> productBoughtList, String receipt, int totalQty, int totalAmount, String cashier) {
        return "Cashier{" +
                "message='" + " Thank you"  + '\'' +
                ", customerName='" + customerName + '\'' +
                ", product='" + getListOfProductBought(productBoughtList) + '\'' +
                ", receipt='" + receipt + '\'' +
                ", totalProductQty='" + totalQty + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", cashier='" + cashier + '\'' +
                '}';
    }

    @Override
    public String getListOfProductBought(List<ProductBought> productBoughtList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < productBoughtList.size(); i++) {
            ProductBought productBought = productBoughtList.get(i);
            if (i == productBoughtList.size() -1) stringBuilder.append(productBought.getProductName());
            else stringBuilder.append(productBought.getProductName()).append(", ");
        }
        return stringBuilder.toString();
    }
}
