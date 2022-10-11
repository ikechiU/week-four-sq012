package service.impl;

import exception.ErrorMessages;
import exception.ProductServiceException;
import model.Customer;
import model.Product;
import model.ProductBought;
import model.Store;
import service.CustomerService;

import java.util.ArrayList;

public class CustomerServiceImpl implements CustomerService {
    private static int customerId = 324;

    @Override
    public String buy(Customer customer, String productName, Store storeProducts, int quantity) {
        setCustomerDetails(customer, productName);
        Double walletBalance = customer.getWalletBalance();

        Product product = getProduct(storeProducts, customer.getProductName());
        checkProductServiceException(product, walletBalance, quantity);
        updateCustomerDetailsAndStoreProducts(customer, walletBalance, product, quantity);
        updateProductBoughtList(customer, quantity, product);

        return "Hello cashier, I will like to buy " + customer.getProductName() + ".";
    }

    private void setCustomerDetails(Customer customer, String productName) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            customer.setId("Cus" + customerId);
            customerId++;
        }
        customer.setProductName(confirmProductName(productName));
    }

    public void checkProductServiceException(Product product, Double walletBalance, int quantity) {
        if (product == null)
            throw new ProductServiceException(ErrorMessages.PRODUCT_NOT_AVAILABLE.getErrorMessage());

        if (walletBalance < (product.getAmount() * quantity))
            throw new ProductServiceException(ErrorMessages.INSUFFICIENT_BALANCE.getErrorMessage());

        if (product.getQuantity() < quantity)
            throw new ProductServiceException(ErrorMessages.OUT_OF_STOCK.getErrorMessage());
    }

    public void updateCustomerDetailsAndStoreProducts(Customer customer, Double walletBalance, Product product, int quantity) {
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

    @Override
    public String buy(Customer customer, String productName, int year, Store storeProducts, int quantity) {
        setCustomerDetails(customer, productName);
        Double walletBalance = customer.getWalletBalance();

        Product product = getProduct(storeProducts, customer.getProductName());
        checkProductServiceException(product, walletBalance, quantity);

        customer.setProductYear(year);
        if (customer.getProductYear() > extractYear(product.getManufactureDate()))
            throw new ProductServiceException(ErrorMessages.MANUFACTURE_YEAR_LESS.getErrorMessage());

        updateCustomerDetailsAndStoreProducts(customer, walletBalance, product, quantity);
        updateProductBoughtList(customer, quantity, product);

        return "Hello cashier, I will like to buy " + customer.getProductName() + ". That was manufactured in " + customer.getProductYear() +".";
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

}
