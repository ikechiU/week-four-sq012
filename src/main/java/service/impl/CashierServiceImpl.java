package service.impl;

import model.*;
import service.CashierService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CashierServiceImpl implements CashierService {

    private static int receipt = 1000;
    private static final int priorityReceipt = 110984235;
    private static final int normalQueueReceipt = 120984235;

    @Override
    public Integer asyncGetQuantitiesOfOneProductSold(Cashier cashier, List<Cart> carts, String specificProductName) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                for (ProductBought product: cart.getProductBoughtList()) {
                    String productName = product.getProductName();
                    cashier.addSpecificProductTotal(specificProductName, productName, product.getQuantity());
                }
                sell(cashier, cart);
            }
            return cashier.getSpecificProductTotal();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer asyncGetQuantitiesSold(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                var totalQty = cart.getTotalQty();
                cashier.addTotalQuantities(totalQty);
                sell(cashier, cart);
            }
            return cashier.getTotalQuantities();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer asyncGetAmountSold(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                var totalAmount = cart.getTotalAmount();
                cashier.addTotalSale(totalAmount);
                sell(cashier, cart);
            }
            return cashier.getTotalSales();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<String> asyncGetCustomerNames(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                var customerName = cart.getCustomerName();
                cashier.addCustomerName(customerName);
                sell(cashier, cart);
            }
            return cashier.getCustomerNames();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> asyncGetProductsSold(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                for (ProductBought productBought: cart.getProductBoughtList()) {
                    cashier.addProductsSold(productBought.getProductName());
                }
                sell(cashier, cart);
            }
            return cashier.getProductsSold();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> asyncGetMapOfProductsSold(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                for (ProductBought productBought: cart.getProductBoughtList()) {
                    var productName = productBought.getProductName();
                    var map = cashier.getProductsSoldMap();
                    int count = 0;
                    if (map.containsKey(productName)) {
                        count = map.get(productName);
                    }
                    cashier.addProductsSoldMap(productName, count + productBought.getQuantity());
                }
                sell(cashier, cart);
            }
            return cashier.getProductsSoldMap();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sales asyncSell(Cashier cashier, List<Cart> carts) {
        var future = CompletableFuture.supplyAsync(() -> {
            for (Cart cart: carts) {
                cashier.addCustomerName(cart.getCustomerName());
                cashier.addTotalSale(cart.getTotalAmount());
                cashier.addTotalQuantities(cart.getTotalQty());

                for (ProductBought productBought: cart.getProductBoughtList()) {
                    var productName = productBought.getProductName();
                    var map = cashier.getProductsSoldMap();
                    int count = 0;
                    if (map.containsKey(productName)) {
                        count = map.get(productName);
                    }
                    cashier.addProductsSoldMap(productName, count + productBought.getQuantity());
                    cashier.addProductsSold(productBought.getProductName());
                }
                sell(cashier, cart);
            }
            return cashier;
        });
        var sales = future.thenApplyAsync(returnedFuture -> new Sales(returnedFuture.getTotalSales(), returnedFuture.getProductsSold(), returnedFuture.getProductsSoldMap(), returnedFuture.getCustomerNames()));
        try {
            return sales.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void sell(Cashier cashier, Cart cart) {
        var cashierName = cashier.getName();
        var customerName = cart.getCustomerName();
        var product = cart.getProductBoughtList();
        var totalQty = cart.getTotalQty();
        var totalAmount = cart.getTotalAmount();
        System.out.println(receipt(customerName, product, String.valueOf(receipt++), totalQty, totalAmount, cashierName));
    }

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
