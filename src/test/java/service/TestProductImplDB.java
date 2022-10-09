package service;

import model.Product;
import service.impl.ProductsDBImpl;
import utils.Docs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestProductImplDB {
    private final List<Product> returnValue = new ArrayList<>();

    public ProductsDBImpl getProductDB() {
        File file = new File("src/main/resources/ProductsCSV.csv");
        var productList = Docs.readCSVUsingScanner(file);

        for (int i = 1; i < productList.size(); i++) {
            String[] arrayProducts = productList.get(i);
            returnValue.add(new Product(arrayProducts[0], arrayProducts[1], arrayProducts[2], arrayProducts[3], arrayProducts[4]));
        }

        ProductsDBImpl productsDB = new ProductsDBImpl();
        productsDB.setProducts(returnValue);

        return productsDB;
    }
}
