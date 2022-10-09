package service.impl;

import model.Product;
import service.ProductDBService;

import java.util.ArrayList;
import java.util.List;

public class ProductsDBImpl implements ProductDBService {

    private List<Product> products;

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
