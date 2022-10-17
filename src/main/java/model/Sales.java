package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sales {
    private Integer amountSold;
    private Set<String> productsSold;
    private Map<String, Integer> mapProductQtySold;
    private Collection<String> customerName;

    public Sales(Integer amountSold, Set<String> productsSold, Map<String, Integer> mapProductQtySold, Collection<String> customerName) {
        this.amountSold = amountSold;
        this.productsSold = productsSold;
        this.mapProductQtySold = mapProductQtySold;
        this.customerName = customerName;
    }

    public Sales() {
    }

    public Integer getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(Integer amountSold) {
        this.amountSold = amountSold;
    }

    public Set<String> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(Set<String> productsSold) {
        this.productsSold = productsSold;
    }

    public Map<String, Integer> getMapProductQtySold() {
        return mapProductQtySold;
    }

    public void setMapProductQtySold(Map<String, Integer> mapProductQtySold) {
        this.mapProductQtySold = mapProductQtySold;
    }

    public Collection<String> getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Collection<String> customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "amountSold=" + amountSold +
                ", productsSold=" + productsSold +
                ", mapProductQtySold=" + mapProductQtySold +
                ", customerName=" + customerName +
                '}';
    }
}
