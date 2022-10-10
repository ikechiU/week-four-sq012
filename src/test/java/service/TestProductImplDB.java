package service;

import model.Product;
import service.impl.ProductsDBImpl;
import utils.Docs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestProductImplDB {

    public ProductsDBImpl getProductDB() {
        File file = new File("src/main/resources/ProductsCSV.csv");
        var productList = Docs.readCSVUsingScanner(file);
        List<Product> returnValue = Docs.getProducts(productList);

        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(returnValue);

        return productsDB;
    }
}
