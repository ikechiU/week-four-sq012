package model;

import service.ManagerService;

public class Manager extends Staff { //Inheritance Staff Class

    private Cashier cashier;
    public Manager(String name, String sex, int age, int id) {
        super(name, sex, age, id);
    }

    public Manager() {

    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }
}
