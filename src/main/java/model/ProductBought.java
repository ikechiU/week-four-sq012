package model;

import java.util.ArrayList;
import java.util.List;

public class ProductBought {
    private String productName;
    private Integer quantity;
    private String itemNo;
    private Double amount;
    private List<ProductBought> productBoughtList = null;

    public ProductBought(String productName, Integer quantity, String itemNo, Double amount) {
        this.productName = productName;
        this.quantity = quantity;
        this.itemNo = itemNo;
        this.amount = amount;
    }

    public ProductBought() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<ProductBought> getProductBoughtList() {
        return productBoughtList;
    }

    public void add(ProductBought productBought) {
        productBoughtList.add(productBought);
    }
}

