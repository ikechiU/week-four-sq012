package model;

public class Customer extends Person { //Inheritance Person Class

    private String productName;
    private int productYear = 0;

    public Customer(String name, String sex, int age) {
        super(name, sex, age);
    }

    public Customer() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

}
