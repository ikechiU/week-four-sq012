package service;

import model.Product;

import java.util.List;

public interface ProductDBService {
    List<Product> getProducts();
    void setProducts(List<Product> products);
}
