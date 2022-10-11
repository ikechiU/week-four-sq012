package service.impl;

import model.*;
import service.CashierService;

import java.util.List;
import java.util.PriorityQueue;

public class CashierServiceImpl implements CashierService {

    private static int receipt = 110984235;

    @Override
    public String priorityQueueSell(Cashier cashier, PriorityQueue<Cart> queue) {
        return getQueueResult(cashier, queue);
    }

    @Override
    public String normalQueueSell(Cashier cashier, PriorityQueue<Cart> queue) {
        return getQueueResult(cashier, queue);
    }

    private String getQueueResult(Cashier cashier, PriorityQueue<Cart> queue) {
        if (queue.isEmpty()) return "Queue is empty";

        String cashierName = cashier.getName();
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            var cart = queue.peek();
            if (cart != null) {
                var customerName = cart.getCustomerName();
                var product = cart.getProductBoughtList();
                var totalQty = cart.getTotalQty();
                var totalAmount = cart.getTotalAmount();
                sb.append(receipt(customerName, product, String.valueOf(receipt), totalQty, totalAmount, cashierName)).append("\n");
                System.out.println(receipt(customerName, product, String.valueOf(receipt), totalQty, totalAmount, cashierName));
            }
            queue.poll();
            receipt++;
        }
        return sb.toString();
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
