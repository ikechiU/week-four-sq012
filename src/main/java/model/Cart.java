package model;

import java.util.List;

public class Cart implements Comparable<Cart>{
    private List<ProductBought> productBoughtList;
    private String orderId;
    private static Integer generateOrderId = 9567;
    private String customerId;
    private String customerName;
    private int totalQty = 0;
    private int totalAmount = 0;

    public Cart(List<ProductBought> productBoughtList, String customerId, String customerName) {
        totalAmount = getTotalAmountOfProductBought(productBoughtList);
        this.productBoughtList = productBoughtList;
        this.customerId = customerId;
        this.customerName = customerName;
        this. orderId = "Order" + generateOrderId;
        generateOrderId++;
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

    private Integer getTotalQtyOfProductBought() {
        for (ProductBought productBought : productBoughtList) {
            totalQty += productBought.getQuantity();
        }
        return totalQty;
    }

    private Integer getTotalAmountOfProductBought(List<ProductBought> productBoughtList) {
        int amount = 0;
        for (ProductBought productBought : productBoughtList) {
            amount += productBought.getAmount();
        }
        return amount;
    }

    @Override
    public int compareTo(Cart o) {
        return  o.getTotalQtyOfProductBought() > getTotalQtyOfProductBought() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", totalProductQty='" + totalQty + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
