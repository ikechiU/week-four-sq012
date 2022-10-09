package service.impl;

import model.Store;
import service.StoreDBService;

public class StoreDBImpl implements StoreDBService {
    @Override
    public Store getStoreProducts(ProductsDBImpl productsDB) {
        return new Store(productsDB.getProducts());
    }
}
