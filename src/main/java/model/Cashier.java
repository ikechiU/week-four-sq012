package model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Cashier extends Staff { //Inheritance Staff Class

    private String receipt;
//    private final Collection<String> customerNames = Collections.synchronizedCollection(new ArrayList<>());
    private final Collection<String> customerNames = new ArrayList<>();
    private final Set<String> productsSold = Collections.synchronizedSet(new HashSet<>());
//    private final Map<String, Integer> productsSoldMap = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Integer> productsSoldMap = new HashMap<>();
    private final Object productSoldMapObjectLock = new Object();
    private final LongAdder totalQuantities = new LongAdder();
//    private final AtomicInteger totalSales = new AtomicInteger();
    private int totalSales;
    private Customer customer;

    private int specificProductTotal;
    private final Object totalQtiesObjectLock = new Object();

    private Sales sales;

    public Cashier(String name, String sex, int age, int id) {
        super(name, sex, age, id);
    }

    public Cashier() {

    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Collection<String> getCustomerNames() {
        return customerNames;
    }

    public void addCustomerName(String customerName) {
        customerNames.add(customerName);
    }

    public Set<String> getProductsSold() {
        return productsSold;
    }

    public void addProductsSold(String product) {
        synchronized (productSoldMapObjectLock) {
            productsSold.add(product);
        }

    }

    public Map<String, Integer> getProductsSoldMap() {
        return productsSoldMap;
    }

    public void addProductsSoldMap(String product, Integer qty) {
        productsSoldMap.put(product, qty);
    }

    public Integer getTotalQuantities() {
        return totalQuantities.intValue();
    }

    public void addTotalQuantities(int qty) {
        this.totalQuantities.add(qty);
    }

//    public Integer getTotalSales() {
//        return totalSales.get();
//    }

    public int getTotalSales() {
        return totalSales;
    }

//    public void setTotalSales() {
//        totalSales.set(0);
//    }

//    public void addTotalSale(int amount) {
//        totalSales.getAndAdd(amount);
//    }

    public void addTotalSale(int amount) {
        totalSales += amount;
    }

    public int getSpecificProductTotal() {
        return specificProductTotal;
    }

    public void addSpecificProductTotal(String specificProductName, String productName, int qty) {
        if (specificProductName.equals(productName)) {
            synchronized (totalQtiesObjectLock) {
                this.specificProductTotal += qty;
            }
        }
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
