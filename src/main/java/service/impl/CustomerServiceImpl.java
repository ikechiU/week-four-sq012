package service.impl;

import exception.ErrorMessages;
import exception.ProductServiceException;
import model.*;
import service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CustomerServiceImpl implements CustomerService {
    private final Object monitorLock = new Object();

    @Override
    public String oldBuy(Customer customer, String productName, Store storeProducts, int quantity) {
        customer.setProductName(confirmProductName(productName));
        Double walletBalance = customer.getWalletBalance();

        Product product = getProduct(storeProducts, customer.getProductName());
        checkProductServiceException(product, walletBalance, quantity);
        updateCustomerAndProductDetails(customer, walletBalance, product, quantity);
        updateProductBoughtList(customer, quantity, product);

        return "Hello cashier, I will like to buy " + customer.getProductName() + ".";
    }

    @Override
    public String oldBuy(Customer customer, HashMap<String, Integer> productNames, Store storeProducts) {
        Double walletBalance = customer.getWalletBalance();

        for (Map.Entry<String, Integer> map : productNames.entrySet()) {
            var productName = map.getKey();
            var qty = map.getValue();
            Product product = getProduct(storeProducts, productName);
            checkProductServiceException(product, walletBalance, qty);
            updateCustomerAndProductDetails(customer, walletBalance, product, qty);
            updateProductBoughtList(customer, productName, qty, product);
        }

        if (productNames.size() == 1)
            return "Hello cashier, I will like to buy " + +customer.getProductBoughtList().size() + " product.";
        else if (productNames.size() > 1)
            return "Hello cashier, I will like to buy " + +customer.getProductBoughtList().size() + " products.";
        else
            return null;
    }

    public void checkProductServiceException(Product product, Double walletBalance, int quantity) {
        if (product == null)
            throw new ProductServiceException(ErrorMessages.PRODUCT_NOT_AVAILABLE.getErrorMessage());

        if (walletBalance < (product.getAmount() * quantity))
            throw new ProductServiceException(ErrorMessages.INSUFFICIENT_BALANCE.getErrorMessage());

        if (product.getQuantity() < quantity)
            throw new ProductServiceException(ErrorMessages.OUT_OF_STOCK.getErrorMessage());
    }

    public void updateCustomerAndProductDetails(Customer customer, Double walletBalance, Product product, int quantity) {
        var newWalletBalance = walletBalance - (product.getAmount() * quantity);
        customer.setWalletBalance(newWalletBalance);
        product.setQuantity(product.getQuantity() - quantity);
        if (product.getQuantity() == 0) product.setStock("OUT OF STOCK");
    }

    public void updateProductBoughtList(Customer customer, int quantity, Product product) {
        ProductBought productBought = new ProductBought(customer.getProductName(), quantity, product.getItemNo(), product.getAmount());
        var list = customer.getProductBoughtList();
        if (list == null) list = new ArrayList<>();
        list.add(productBought);
        customer.setProductBoughtList(list);
    }

    public void updateProductBoughtList(Customer customer, String productName, int quantity, Product product) {
        ProductBought productBought = new ProductBought(productName, quantity, product.getItemNo(), product.getAmount());
        var list = customer.getProductBoughtList();
        if (list == null) list = new ArrayList<>();
        list.add(productBought);
        customer.setProductBoughtList(list);
    }

    @Override
    public String oldBuy(Customer customer, String productName, int year, Store storeProducts, int quantity) {
        customer.setProductName(confirmProductName(productName));
        Double walletBalance = customer.getWalletBalance();

        Product product = getProduct(storeProducts, customer.getProductName());
        checkProductServiceException(product, walletBalance, quantity);

        customer.setProductYear(year);
        if (customer.getProductYear() > extractYear(product.getManufactureDate()))
            throw new ProductServiceException(ErrorMessages.MANUFACTURE_YEAR_LESS.getErrorMessage());

        updateCustomerAndProductDetails(customer, walletBalance, product, quantity);
        updateProductBoughtList(customer, quantity, product);

        return "Hello cashier, I will like to buy " + customer.getProductName() + ". That was manufactured in " + customer.getProductYear() + ".";
    }

    private List<Product> newBuyProducts(List<Customer> customers, Store storeProducts) {
        for (var customer : customers) {
            newBuyLogic(customer, storeProducts);
        }
        return storeProducts.getProducts();
    }

    private List<Customer> newBuyCustomers(List<Customer> customers, Store storeProducts) {
        for (var customer : customers) {
            newBuyLogic(customer, storeProducts);
        }
        return customers;
    }

    private void newBuyLogic(Customer customer, Store storeProducts) {
        synchronized (monitorLock) {
            for (Map.Entry<String, Integer> map : customer.getCustomerCart().getCart().entrySet()) {
                var productName = map.getKey();
                var qty = map.getValue();
                Double walletBalance = customer.getWalletBalance();
                Product product = getProduct(storeProducts, productName);
                var result = checkError(product, walletBalance, qty);

                if (result.equals("OK")) {
                    updateCustomerAndProductDetails(customer, walletBalance, product, qty);
                    customer.addToCartStatus(productName, qty.toString());
                } else {
                    customer.addToCartStatus(productName, result);
                }
            }
        }
    }

    public String checkError(Product product, Double walletBalance, int quantity) {
        if (product == null)
            return ErrorMessages.PRODUCT_NOT_AVAILABLE.getErrorMessage();

        if (walletBalance < (product.getAmount() * quantity))
            return ErrorMessages.INSUFFICIENT_BALANCE.getErrorMessage();

        if (product.getQuantity() < quantity) {
            if (product.getQuantity() == 0)  return ErrorMessages.OUT_OF_STOCK.getErrorMessage();
            else return "Change quantity, only " + product.getQuantity() + " available in store.";
        }
        return "OK";
    }

    @Override
    public List<Product> buyAsyncThread(List<Customer> customers, Store storeProducts) {
        var future = CompletableFuture.supplyAsync(() -> newBuyProducts(customers, storeProducts));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return storeProducts.getProducts();
    }

    @Override
    public List<Product> buyNonAsyncThread(List<Customer> customers, Store storeProducts) {
        List<Thread> threads = new ArrayList<>();
        for (var customer : customers) {
            Thread thread = new Thread(new NonAsync(customer, storeProducts));
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return storeProducts.getProducts();
    }

    @Override
    public List<Customer> newBuyAsyncThread(List<Customer> customers, Store storeProducts) {
        var future = CompletableFuture.supplyAsync(() -> newBuyCustomers(customers, storeProducts));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> newBuyNonAsyncThread(List<Customer> customers, Store storeProducts) {
        List<Thread> threads = new ArrayList<>();
        for (var customer : customers) {
            Thread thread = new Thread(new NonAsync(customer, storeProducts));
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }


    @Override
    public String confirmProductName(String productName) {
        return String.valueOf(productName.charAt(0)).toUpperCase() + productName.substring(1).toLowerCase();
    }

    @Override
    public Product getProduct(Store storeProducts, String productName) {
        for (Product product : storeProducts.getProducts()) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }

        return null;
    }


    @Override
    public int extractYear(String year) {
        year = year.substring(6);
        return Integer.parseInt(year);
    }

    public static class NonAsync implements Runnable {
        private final Customer customer;
        private final Store storeProducts;

        public NonAsync(Customer customer, Store storeProducts) {
            this.customer = customer;
            this.storeProducts = storeProducts;
        }

        @Override
        public void run() {
            CustomerServiceImpl customerService = new CustomerServiceImpl();
            customerService.newBuyLogic(customer, storeProducts);
        }
    }

}
