package utils;
import model.Cart;

import java.util.Comparator;

public class CartNormalComparator implements Comparator<Cart> {

    @Override
    public int compare(Cart o1, Cart o2) {
        if (o1.getFifoQueue() < o2.getFifoQueue()) return 0;
        else if (o1.getFifoQueue() > o2.getFifoQueue()) return 0;
        else return 0;
    }
}