package model;

public class Product {
    private String name;
    private String category;
    private Integer quantity;
    private String manufactureDate;
    private String expiryDate;
    private String itemNo;
    private String stock;
    private Double amount;

    public Product(String name, String category, Integer quantity, String manufactureDate, String expiryDate, String itemNo, String stock, Double amount) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.itemNo = itemNo;
        this.stock = stock;
        this.amount = amount;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
