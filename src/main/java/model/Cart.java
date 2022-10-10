package model;

import java.util.List;

//public class Cart implements Comparable<Cart>{
public class Cart {
    private List<ProductBought> productBoughtList;
    private String orderId;
    private static Integer generateOrderId = 9567;
    private String customerId;
    private String customerName;
    private int totalQty;
    private int totalAmount = 0;

    public Cart(List<ProductBought> productBoughtList, String customerId, String customerName) {
        totalAmount = getTotalAmountOfProductBought(productBoughtList);
        totalQty = getTotalQtyOfProductBought(productBoughtList);
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

    private Integer getTotalQtyOfProductBought(List<ProductBought> productBoughtList) {
        int total = 0;
        for (ProductBought productBought : productBoughtList) {
            total += productBought.getQuantity();
        }
        return total;
    }

    private Integer getTotalAmountOfProductBought(List<ProductBought> productBoughtList) {
        int amount = 0;
        for (ProductBought productBought : productBoughtList) {
            amount += productBought.getAmount();
        }
        return amount;
    }

//    @Override
//    public int compareTo(Cart o) {
//        return  o.totalQty > this.totalQty ? 1 : -1;
//    }

//    @Override
//    public String toString() {
//        return "Cart{" +
//                "customerName='" + customerName + '\'' +
//                ", customerId='" + customerId + '\'' +
//                ", orderId='" + orderId + '\'' +
//                ", totalProductQty='" + totalQty + '\'' +
//                ", totalAmount='" + totalAmount + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Cart{" +
                "customerName='" + customerName + '\'' +
                ", qty='" + totalQty + '\'' +
                '}';
    }
}
