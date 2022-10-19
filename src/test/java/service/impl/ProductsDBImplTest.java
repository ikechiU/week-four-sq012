package service.impl;

import model.Product;
import model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsDBImplTest {

    Store store;

    @BeforeEach
    void init() {
        TestProductImplDB productImplDB = new TestProductImplDB();
        StoreDBImpl storeDB = new StoreDBImpl();
        store = storeDB.getStoreProducts(productImplDB.getProductDB());
    }

    @Test
    void getProducts() {
        List<Product> products = store.getProducts();
        assertEquals(products.get(0).getName(), "Rice");
    }
}