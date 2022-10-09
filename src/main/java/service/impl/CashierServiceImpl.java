package service.impl;

import model.Cashier;
import model.Product;
import model.Store;
import service.CashierService;

import java.util.Calendar;

public class CashierServiceImpl implements CashierService {

    @Override
    public String sell(Cashier cashier, String productName) {
        cashier.setReceipt(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        return "Thank you " + cashier.getCustomer().getName() + " for buying " + cashier.getCustomer().getProductName() +
                ". Here is your receipt " + cashier.getReceipt() + ".";
    }
}
