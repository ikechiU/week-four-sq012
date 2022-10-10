package utils;
import model.Cart;

import java.util.Comparator;

public class CartComparator implements Comparator<Cart> {

    @Override
    public int compare(Cart o1, Cart o2)
    {
        return o1.getTotalQty() < o2.getTotalQty() ? 1 : -1;
    }
}