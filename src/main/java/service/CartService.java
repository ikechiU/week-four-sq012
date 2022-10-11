package service;

import model.Cart;
import model.ProductBought;
import java.util.List;

public interface CartService {
    Cart createCart(List<ProductBought> productBoughtList, String customerId, String customerName);

    Integer getTotalQtyOfProductBought(List<ProductBought> productBoughtList);

    Integer getTotalAmountOfProductBought(List<ProductBought> productBoughtList);
}

