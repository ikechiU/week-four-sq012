package service.impl;

import model.Applicant;
import model.Cashier;
import model.Manager;
import service.ManagerService;
import utils.Qualification;

import java.util.Objects;

public class ManagerServiceImpl implements ManagerService {

    @Override
    public Cashier hireCashier(Applicant applicant, Manager manager) {
        String qualification = applicant.getQualification();

        if (qualification.equals(Qualification.SSCE.name()))
            return null;

        Cashier cashier = new Cashier(applicant.getName(), applicant.getSex(), applicant.getAge(), 222);
        manager.setCashier(cashier);
        return cashier;
    }
}
