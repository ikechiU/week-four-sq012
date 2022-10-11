package service.impl;

import exception.ErrorMessages;
import exception.ProductServiceException;
import model.Customer;
import model.Product;
import model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private Store storeProducts;
    Product product;
    Product product1;
    Product product2;
    CustomerServiceImpl customerService;
    Customer customer;
    Customer customer2;

    @BeforeEach
    void init() {
        TestProductImplDB productImplDB = new TestProductImplDB();
        storeProducts = new StoreDBImpl().getStoreProducts(productImplDB.getProductDB());
        product = storeProducts.getProducts().get(0);
        product1 = storeProducts.getProducts().get(1);
        product2 = storeProducts.getProducts().get(2);
        customerService = new CustomerServiceImpl();
        customer = new Customer("Henry", "Male", 43, 2000.0);
        customer2 = new Customer("Angela", "Female", 21, 3500.0);
    }

    @Test
    void buy() {
        String result = customerService.buy(customer, "BeAnS", storeProducts, 1);
        String result1 = customerService.buy(customer2, "rIcE", storeProducts, 1);

        assertEquals(result, "Hello cashier, I will like to buy " + customer.getProductName() + ".");
        assertEquals(result1, "Hello cashier, I will like to buy " + customer2.getProductName() + ".");
    }

    @Test
    void BuyOverLoaded() {
        String result = customerService.buy(customer, "BeAnS", 2022, storeProducts, 1);
        assertEquals(result,
                "Hello cashier, I will like to buy " + customer.getProductName() + ". That was manufactured in " + customer.getProductYear() +".");
    }

    @Test
    void updateCustomerDetails() {
        customerService.buy(customer, "BeAnS", storeProducts, 1);
        customerService.buy(customer2, "rIcE", storeProducts, 1);

        var newCustomerBalance = customer.getWalletBalance();
        var newCustomer2Balance = customer2.getWalletBalance();

        assertEquals(newCustomerBalance, 2000.0 - 1300);
        assertEquals(newCustomer2Balance, 3500.0 - 1500);

    }

    @Test
    void updateStoreProducts() {
        customerService.buy(customer, "BeAnS", storeProducts, 1);
        customerService.buy(customer2, "rIcE", storeProducts, 1);

        var riceStockQty = storeProducts.getProducts().get(0).getQuantity();
        var beansStockQty = storeProducts.getProducts().get(0).getQuantity();

        assertEquals(riceStockQty, 19);
        assertEquals(beansStockQty, 19);
    }

    @Test
    void updateProductBoughtList() {
        customerService.buy(customer2, "BeAnS", storeProducts, 1);
        customerService.buy(customer2, "rIcE", storeProducts, 1);

        var list = customer2.getProductBoughtList();
        assertEquals(list.size(), 2);
    }

    @Test
    void buyProductInsufficientFundTesting() {
        ProductServiceException thrown = assertThrows(ProductServiceException.class, ()->
                customerService.buy(customer, "rice", storeProducts, 5),
                ErrorMessages.INSUFFICIENT_BALANCE.name());
        assertTrue(thrown.getMessage().contains("Insufficient balance."));
    }


    @Test
    void buyProductYearExceptionTesting() {
        ProductServiceException thrown = assertThrows(ProductServiceException.class, ()->
                customerService.buy(customer2, "rIcE", 2033, storeProducts, 1),
                ErrorMessages.MANUFACTURE_YEAR_LESS.name());

        assertTrue(thrown.getMessage().contains("Product manufacture year lesser than your request."));
    }

    @Test
    void buyProductOutOfStockExceptionTesting() {
        ProductServiceException thrown = assertThrows(ProductServiceException.class, ()->
                        customerService.buy(customer2, "Fanta", 2022, storeProducts, 1),
                ErrorMessages.OUT_OF_STOCK.name());

        assertTrue(thrown.getMessage().contains("Product out of stock."));
    }

    @Test
    void buyProductNotAvailableExceptionTesting() {
        ProductServiceException thrown = assertThrows(ProductServiceException.class, ()->
                        customerService.buy(customer2, "Suya", 2022, storeProducts, 1),
                ErrorMessages.PRODUCT_NOT_AVAILABLE.name());

        assertTrue(thrown.getMessage().contains("Product not available."));
    }


    @Test
    void confirmProductName() {
        String productName = customerService.confirmProductName("BeANs");
        String productName1 = customerService.confirmProductName("RicE");
        String productName2 = customerService.confirmProductName("CAsHeW");
        assertEquals("Beans", productName);
        assertEquals("Rice", productName1);
        assertEquals("Cashew", productName2);
    }

    @Test
    void getProduct() {
        for (Product aProduct : storeProducts.getProducts()) {
            if (aProduct.getName().equals("Rice")) {
                product2 = aProduct;
            }
        }

        Product product3 = null;
        for (Product aProduct : storeProducts.getProducts()) {
            if (aProduct.getName().equals("Cashew")) {
                product3 = aProduct;
            }
        }

        assertEquals(product, product2);
        assertNotEquals(product1, product2);
        assertNull(product3);
    }

    @Test
    void extractYear() {
        String year = "02-02-2020";
        String year1 = "02-02-2023";
        String year2 = "02-02-2024";

        assertEquals("2020", String.valueOf(customerService.extractYear(year)));
        assertEquals("2023", String.valueOf(customerService.extractYear(year1)));
        assertEquals("2024", String.valueOf(customerService.extractYear(year2)));

        String year3 = null;
        for (Product aProduct: storeProducts.getProducts()) {
            if (aProduct.getName().equals("Carrot")) {
                year3 = aProduct.getManufactureDate();
            }
        }
        assertEquals("2022", String.valueOf(customerService.extractYear(year3)));
    }

}