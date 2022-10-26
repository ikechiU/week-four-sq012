package utils;

import model.Customer;
import model.CustomerCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomersList {
    private final Customer customer = new Customer("Henry", "Male", 43, 10000.0);
    private final Customer customer1 = new Customer("Yinka", "Male", 43, 250000.0);
    private final Customer customer2 = new Customer("Agnes", "Female", 20, 25000.0);
    private final Customer customer3 = new Customer("Agatha", "Female", 30, 110500.0);
    private final Customer customer4 = new Customer("Ben", "Male", 31, 905000.0);
    private final Customer customer5 = new Customer("Jim", "Male", 32, 900000.0);
    private final Customer customer6 = new Customer("Simi", "Female", 32, 80000.0);
    private final Customer customer7 = new Customer("Anne", "Female", 39, 850000.0);
    private final Customer customer8 = new Customer("Sam", "Male", 79, 550000.0);
    private final Customer customer9 = new Customer("Nwachukwu", "Male", 89, 65000.0);
    private final Customer customer10 = new Customer("Joy", "Female", 49, 7500.0);

    public List<Customer> getCustomers() {
        HashMap<String, Integer> customerProductList = new HashMap<>();
        customerProductList.put("Rice", 2);
        customerProductList.put("Beans", 4);
        customerProductList.put("Suya", 10);
        customerProductList.put("Yogurt", 2);
        customerProductList.put("Coke", 5);
        customer.setCustomerCart(new CustomerCart(customerProductList));

        HashMap<String, Integer> customerProductList1 = new HashMap<>();
        customerProductList1.put("Rice", 20);
        customerProductList1.put("Beans", 2);
        customerProductList1.put("Pasta", 2);
        customerProductList1.put("Jam", 2);
        customer1.setCustomerCart(new CustomerCart(customerProductList1));

        HashMap<String, Integer> customerProductList2 = new HashMap<>();
        customerProductList2.put("Rice", 10);
        customerProductList2.put("Beans", 1);
        customerProductList2.put("Pasta", 1);
        customerProductList2.put("Jam", 1);
        customer2.setCustomerCart(new CustomerCart(customerProductList2));

        HashMap<String, Integer> customerProductList3 = new HashMap<>();
        customerProductList3.put("Coffee", 10);
        customerProductList3.put("Eggs", 30);
        customerProductList3.put("Pasta", 2);
        customerProductList3.put("Jam", 2);
        customer3.setCustomerCart(new CustomerCart(customerProductList3));

        HashMap<String, Integer> customerProductList4 = new HashMap<>();
        customerProductList4.put("Cheese", 2);
        customerProductList4.put("Tea", 2);
        customerProductList4.put("Milk", 3);
        customer4.setCustomerCart(new CustomerCart(customerProductList4));

        HashMap<String, Integer> customerProductList5 = new HashMap<>();
        customerProductList5.put("Heineken", 5);
        customerProductList5.put("TV", 8);
        customerProductList5.put("Coffee", 8);
        customer5.setCustomerCart(new CustomerCart(customerProductList5));

        HashMap<String, Integer> customerProductList6 = new HashMap<>();
        customerProductList6.put("Milk", 15);
        customerProductList6.put("Apples", 2);
        customerProductList6.put("Onion", 2);
        customer6.setCustomerCart(new CustomerCart(customerProductList6));

        HashMap<String, Integer> customerProductList7 = new HashMap<>();
        customerProductList7.put("Spaghetti", 2);
        customerProductList7.put("Maggi", 2);
        customerProductList7.put("Heineken", 2);
        customer7.setCustomerCart(new CustomerCart(customerProductList7));

        HashMap<String, Integer> customerProductList8 = new HashMap<>();
        customerProductList8.put("Cucumber", 2);
        customerProductList8.put("Peppers", 3);
        customerProductList8.put("Malt", 3);
        customerProductList8.put("Milk", 3);
        customer8.setCustomerCart(new CustomerCart(customerProductList8));

        HashMap<String, Integer> customerProductList9 = new HashMap<>();
        customerProductList9.put("Mandarins", 4);
        customerProductList9.put("Mango", 1);
        customerProductList9.put("Fanta", 1);
        customerProductList9.put("Wet wipes", 19);
        customer9.setCustomerCart(new CustomerCart(customerProductList9));

        HashMap<String, Integer> customerProductList10 = new HashMap<>();
        customerProductList10.put("Strawberries", 3);
        customerProductList10.put("Blueberries", 10);
        customer10.setCustomerCart(new CustomerCart(customerProductList10));


        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);
        customers.add(customer10);

        return customers;
    }
}
