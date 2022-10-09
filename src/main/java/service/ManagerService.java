package service;

import model.Applicant;
import model.Cashier;
import model.Manager;

public interface ManagerService {
    Cashier hireCashier(Applicant applicant, Manager manager); //Abstraction
}
