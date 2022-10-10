import model.*;
import service.impl.*;
import model.Cart;
import utils.Docs;
import utils.Qualification;

import java.io.File;
import java.util.*;

public class Main {
    private static final ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("src/main/resources/ProductsCSV.csv");

        var productList = Docs.readCSVUsingScanner(file);
        //var productList = Docs.readCSVUsingBufferedReader(file);
        for (int i = 1; i < productList.size(); i++) {
            String[] arrayProducts = productList.get(i);
            System.out.println(Arrays.toString(arrayProducts));
            products.add(new Product(arrayProducts[0], arrayProducts[1], Integer.parseInt(arrayProducts[2]), arrayProducts[3], arrayProducts[4], arrayProducts[5], arrayProducts[6], Double.parseDouble(arrayProducts[7])));
        }

        storeActions(products);
    }

    private static void storeActions(ArrayList<Product> products) {
        Queue<String> customerQueue = new LinkedList<>();
        Queue<Cart> priorityQueue = new PriorityQueue<>();
        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(products);

        Manager manager = new Manager("Michael", "Male", 32, 1);
        Applicant applicant = new Applicant("Doris", "Female", 25, Qualification.SSCE.name());
        Applicant applicant1 = new Applicant("Smith", "Male", 28, Qualification.BSC.name());
        Cashier cashier = new Cashier("Doris", "Female", 25, 2); //Polymorphism
        Customer customer = new Customer("Henry", "Male", 43, 2111800.0);
        Customer customer1 = new Customer("Yinka", "Male", 43, 25000.0);
        Customer customer3 = new Customer("Agatha", "Female", 30, 11500.0);

        ManagerServiceImpl managerService = new ManagerServiceImpl();
        CashierServiceImpl cashierService = new CashierServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        StoreDBImpl storeDB = new StoreDBImpl();

        Store storeProducts = storeDB.getStoreProducts(productsDB);

        Cashier cashierToHire = managerService.hireCashier(applicant, manager);
        if (cashierToHire == null)
            System.out.println("\nQualification less than BSC or MSC\n");

        Cashier cashier2 = managerService.hireCashier(applicant1, manager);

        //First Customer Buy Product 1
        String customerBuyResult = customerService.buy(customer, "RICE", storeProducts, 16);
        System.out.println(customerBuyResult);
        cashier.setCustomer(customer);
        String cashierSellResult = cashierService.sell(cashier, customer.getProductName());
        System.out.println(cashierSellResult + "\n");
        //First Customer Buy Product 2
        String customerBuyResult2 = customerService.buy(customer, "BEANS", storeProducts, 3);
        System.out.println(customerBuyResult2);
        cashier.setCustomer(customer);
        String cashierSellResult2 = cashierService.sell(cashier, customer.getProductName());
        System.out.println(cashierSellResult2 + "\n");
        //First Customer Product added to Cart
        Cart customerCart = new Cart(customer.getProductBoughtList(), customer.getId(), customer.getName());


        //Second Customer Buy Product 1
        String customer1BuyResult = customerService.buy(customer1, "RICE", 2022, storeProducts, 2);
        System.out.println(customer1BuyResult);
        cashier2.setCustomer(customer1);
        String cashier1SellResult = cashierService.sell(cashier2, customer1.getProductName());
        System.out.println(cashier1SellResult + "\n");
        //Second Customer Product added to Cart
        Cart customerCart1 = new Cart(customer1.getProductBoughtList(), customer1.getId(), customer1.getName());
        priorityQueue.add(customerCart1);
        customerQueue.add(customer1.getName());

        //Third Customer Buy Product 1
        String customer3BuyResult = customerService.buy(customer3, "Cod", 2022, storeProducts, 1);
        System.out.println(customer3BuyResult);
        cashier2.setCustomer(customer3);
        String cashier3SellResult = cashierService.sell(cashier2, customer3.getProductName());
        System.out.println(cashier3SellResult + "\n");
        //Third Customer Product added to Cart
        Cart customerCart3 = new Cart(customer3.getProductBoughtList(), customer3.getId(), customer3.getName());
        priorityQueue.add(customerCart3);
        customerQueue.add(customer3.getName());

        //Customers buying products that throws exception
        Customer customer2 = new Customer("Angela", "Female", 21, 1000.0);
        Customer customer4 = new Customer("Herschel", "Male", 50, 10000.0);

        //buyExceptions1(customerService, customer2, storeProducts);
        //buyExceptions2(customerService, customer4, storeProducts);

        priorityQueue.add(customerCart);
        customerQueue.add(customer.getName());
        
        System.out.println("\n\n" + priorityQueue);
        System.out.println("\n" + customerQueue);
    }

    public static void buyExceptions1(CustomerServiceImpl customerService, Customer customer2, Store storeProducts) {
        String customer2BuyResult = customerService.buy(customer2, "beankS", storeProducts, 1);
        System.out.println(customer2BuyResult);
    }

    public static void buyExceptions2(CustomerServiceImpl customerService, Customer customer4, Store storeProducts) {
        String customer4BuyResult = customerService.buy(customer4, "bEaNs", 2025, storeProducts, 2);
        System.out.println(customer4BuyResult);
    }
}
