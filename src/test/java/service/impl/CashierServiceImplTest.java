package service.impl;

import model.Cashier;
import model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CashierServiceImplTest {
    CashierServiceImpl cashierService;
    Customer customer;
    Cashier cashier;

    @BeforeEach
    public void init() {
        cashierService = new CashierServiceImpl();
        customer = new Customer("Kendrick", "Female", 19, 20000.0);
        customer.setProductName("Rice");
        cashier = new Cashier();
        cashier.setCustomer(customer);
    }

    @Test
    void sell() {
        String result = cashierService.sell(cashier, customer.getProductName());
        String receipt = cashier.getReceipt();
        Assertions.assertEquals(
                result,
                "Thank you " + cashier.getCustomer().getName() + " for buying " +
                        cashier.getCustomer().getProductName() + ". Here is your receipt " + receipt + "."
        );

    }

}