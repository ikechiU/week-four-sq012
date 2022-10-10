package model;

import java.util.List;

public class Customer extends Person { //Inheritance Person Class

    private String productName;
    private Integer productYear = 0;
    private String id;
    private static Integer idGenerator = 1000;
    private Double walletBalance;
    public List<ProductBought> productBoughtList = null;

    public Customer(String name, String sex, int age, Double walletBalance) {
        super(name, sex, age);
        this.walletBalance = walletBalance;
        this.id = "Cus" + idGenerator;
        idGenerator++;
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
}
