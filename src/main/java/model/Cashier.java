package model;

public class Cashier extends Staff { //Inheritance Staff Class

    private String receipt;

    public Cashier(String name, String sex, int age, int id) {
        super(name, sex, age, id);
    }

    public Cashier() {

    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

}
