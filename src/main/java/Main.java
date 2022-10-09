import model.*;
import service.impl.*;
import utils.Docs;
import utils.Qualification;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    private static final ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("src/main/resources/ProductsCSV.csv");

        var productList = Docs.readCSVUsingScanner(file);
        //var productList = Docs.readCSVUsingBufferedReader(file);
        for (int i = 1; i < productList.size(); i++) {
            String[] arrayProducts = productList.get(i);
            System.out.println(Arrays.toString(arrayProducts));
            products.add(new Product(arrayProducts[0], arrayProducts[1], arrayProducts[2], arrayProducts[3], arrayProducts[4]));
        }

        storeActions(products);
    }

    private static void storeActions(ArrayList<Product> products) {
        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(products);

        Manager manager = new Manager("Michael", "Male", 32, 1);
        Applicant applicant = new Applicant("Doris", "Female", 25, Qualification.SSCE.name());
        Applicant applicant1 = new Applicant("Smith", "Male", 28, Qualification.BSC.name());
        Cashier cashier = new Cashier("Doris", "Female", 25, 2); //Polymorphism
        Customer customer = new Customer("Henry", "Male", 43);
        Customer customer3 = new Customer("Agatha", "Female", 30);

        ManagerServiceImpl managerService = new ManagerServiceImpl();
        CashierServiceImpl cashierService = new CashierServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        StoreDBImpl storeDB = new StoreDBImpl();

        Store storeProducts = storeDB.getStoreProducts(productsDB);

        Cashier cashierToHire = managerService.hireCashier(applicant, manager);
        if (cashierToHire == null)
            System.out.println("\nQualification less than BSC or MSC\n");

        Cashier cashier2 = managerService.hireCashier(applicant1, manager);

        String customerBuyResult = customerService.buy(customer, "RICE", storeProducts);
        System.out.println(customerBuyResult);

        cashier.setCustomer(customer);
        String cashierSellResult = cashierService.sell((Cashier) cashier, customer.getProductName());
        System.out.println(cashierSellResult + "\n");

        String customer3BuyResult = customerService.buy(customer3, "RICE", 2022, storeProducts);
        System.out.println(customer3BuyResult);

        assert cashier2 != null;
        cashier2.setCustomer(customer3);
        String cashier3SellResult = cashierService.sell(cashier2, customer3.getProductName());
        System.out.println(cashier3SellResult + "\n");

        Customer customer2 = new Customer("Angela", "Female", 21);
        Customer customer4 = new Customer("Herschel", "Male", 50);

        //buyExceptions1(customerService, customer2, storeProducts);
        //buyExceptions2(customerService, customer4, storeProducts);
    }

    public static void buyExceptions1(CustomerServiceImpl customerService, Customer customer2, Store storeProducts) {
        String customer2BuyResult = customerService.buy(customer2, "beankS", storeProducts);
        System.out.println(customer2BuyResult);
    }

    public static void buyExceptions2(CustomerServiceImpl customerService, Customer customer4, Store storeProducts) {
        String customer4BuyResult = customerService.buy(customer4, "bEaNs", 2025, storeProducts);
        System.out.println(customer4BuyResult);
    }
}
