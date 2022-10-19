package service.impl;

import model.Applicant;
import model.Cashier;
import model.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Qualification;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class ManagerServiceImplTest {
    Applicant applicant;
    Applicant applicant1;
    Manager manager;
    ManagerServiceImpl managerService;

    @BeforeEach
    void init() {
        applicant = new Applicant("Doris", "Female", 25, Qualification.SSCE.name());
        applicant1 = new Applicant("Smith", "Male", 28, Qualification.BSC.name());
        manager = new Manager("Michael", "Male", 32, 1);
        managerService = new ManagerServiceImpl();
    }

    @Test
    void hireCashier() {
        Cashier cashier = managerService.hireCashier(applicant1, manager);

        assertNull(managerService.hireCashier(applicant, manager));
        assertEquals(cashier.getName(), "Smith");
        assertEquals(cashier.getId(), 222);
        assertEquals(cashier.getSex(), "Male");
    }
}