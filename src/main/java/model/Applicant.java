package model;

public class Applicant extends Person{
    private String qualification;

    public Applicant(String name, String sex, int age, String qualification) {
        super(name, sex, age);
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
