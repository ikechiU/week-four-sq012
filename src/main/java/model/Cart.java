package model;

import java.util.List;

public class Cart {
    private List<ProductBought> productBoughtList;
    private String orderId;
    private String customerId;
    private String customerName;
    private int totalQty;
    private int totalAmount;
    private int fifoQueue = 0;

    public Cart(List<ProductBought> productBoughtList, String customerId, String customerName) {
        this.productBoughtList = productBoughtList;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public Cart() {

    }

    public List<ProductBought> getProductBoughtList() {
        return productBoughtList;
    }

    public void setProductBoughtList(List<ProductBought> productBoughtList) {
        this.productBoughtList = productBoughtList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public void setFifoQueue(int fifoQueue) {
        this.fifoQueue = fifoQueue;
    }

    public int getFifoQueue() {
        return fifoQueue;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "qty='" + totalQty + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
