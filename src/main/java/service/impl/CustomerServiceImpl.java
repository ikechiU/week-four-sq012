package service.impl;

import exception.ErrorMessages;
import exception.ProductServiceException;
import model.Customer;
import model.Product;
import model.Store;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public String buy(Customer customer, String productName, Store storeProducts) {
        customer.setProductName(confirmProductName(productName));

        Product product = getProduct(storeProducts, customer.getProductName());
        if (product == null)
            throw new ProductServiceException(ErrorMessages.PRODUCT_NOT_AVAILABLE.getErrorMessage());

        if (product.getCategory().equals("OUT OF STOCK"))
            throw new ProductServiceException(ErrorMessages.OUT_OF_STOCK.getErrorMessage());

        return "Hello cashier, I will like to buy " + customer.getProductName() + ".";
    }

    @Override
    public String buy(Customer customer, String productName, int year, Store storeProducts) {
        customer.setProductName(confirmProductName(productName));

        Product product = getProduct(storeProducts, customer.getProductName());
        if (product == null)
            throw new ProductServiceException(ErrorMessages.PRODUCT_NOT_AVAILABLE.getErrorMessage());

        if (product.getQuantity().equals("OUT OF STOCK"))
            throw new ProductServiceException(ErrorMessages.OUT_OF_STOCK.getErrorMessage());

        customer.setProductYear(year);
        if (customer.getProductYear() > extractYear(product.getManufactureDate()))
            throw new ProductServiceException(ErrorMessages.MANUFACTURE_YEAR_LESS.getErrorMessage());

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
