package service;

import model.Store;
import service.impl.ProductsDBImpl;

public interface StoreDBService {
    Store getStoreProducts(ProductsDBImpl productsDB);
}
