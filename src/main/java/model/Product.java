package model;

public class Product {
    private String name;
    private String category;
    private String quantity;
    private String manufactureDate;
    private String expiryDate;

    public Product(String name, String category, String quantity, String manufactureDate, String expiryDate) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
}
