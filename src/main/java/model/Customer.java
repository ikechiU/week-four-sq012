package model;

import java.util.HashMap;
import java.util.List;

public class Customer extends Person { //Inheritance Person Class

    private static int customerId = 324;
    private String productName;
    private Integer productYear = 0;
    private String id;
    private Double walletBalance;
    public List<ProductBought> productBoughtList;
    public CustomerCart customerCart;
    private HashMap<String, String> cartStatus = new HashMap<>();
    private HashMap<String, Integer> purchase = new HashMap<>();


    public Customer(String name, String sex, int age, Double walletBalance) {
        super(name, sex, age);
        this.walletBalance = walletBalance;
        setId(String.valueOf(customerId));
        customerId++;
    }

    public Customer() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductYear() {
        return productYear;
    }

    public void setProductYear(Integer productYear) {
        this.productYear = productYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<ProductBought> getProductBoughtList() {
        return productBoughtList;
    }

    public void setProductBoughtList(List<ProductBought> productBoughtList) {
        this.productBoughtList = productBoughtList;
    }

    public CustomerCart getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(CustomerCart customerCart) {
        this.customerCart = customerCart;
    }

    public HashMap<String, String> getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(HashMap<String, String> cartStatus) {
        this.cartStatus = cartStatus;
    }

    public void addToCartStatus(String productName, String status) {
        cartStatus.put(productName, status);
    }

    public HashMap<String, Integer> getPurchase() {
        return purchase;
    }

    public void setPurchase(HashMap<String, Integer> purchase) {
        this.purchase = purchase;
    }

    public void addToPurchase(String productName, Integer quantity) {
        purchase.put(productName, quantity);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cartStatus=" + cartStatus +
                ", purchase=" + purchase +
                ", walletBalance=" + walletBalance +
                '}';
    }
}
