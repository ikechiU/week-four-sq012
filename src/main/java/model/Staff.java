package model;

import model.Person;

public class Staff extends Person {
    private int id;

    public Staff(String name, String sex, int age, int id) {
        super(name, sex, age);
        this.id = id;
    }

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
