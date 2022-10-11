package service.impl;

import model.Cart;
import model.ProductBought;
import service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private static int orderId = 1000;

    @Override
    public Cart createCart(List<ProductBought> productBoughtList, String customerId, String customerName) {
        Cart cart = new Cart(productBoughtList, customerId, customerName);
        cart.setTotalQty(getTotalQtyOfProductBought(productBoughtList));
        cart.setTotalAmount(getTotalAmountOfProductBought(productBoughtList));
        cart.setOrderId("Order" + orderId);
        orderId++;
        return cart;
    }

    @Override
    public Integer getTotalQtyOfProductBought(List<ProductBought> productBoughtList) {
        int total = 0;
        for (ProductBought productBought : productBoughtList) {
            total += productBought.getQuantity();
        }
        return total;
    }

    @Override
    public Integer getTotalAmountOfProductBought(List<ProductBought> productBoughtList) {
        int amount = 0;
        for (ProductBought productBought : productBoughtList) {
            amount += (productBought.getAmount() * productBought.getQuantity());
        }
        return amount;
    }

}
