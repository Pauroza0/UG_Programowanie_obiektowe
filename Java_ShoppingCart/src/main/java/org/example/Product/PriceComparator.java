package org.example.Product;

import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    private final boolean ascending;

    public PriceComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Product p1, Product p2) {
        int priceComp;
        if (ascending) {
            priceComp =  Double.compare(p1.getPrice(), p2.getPrice());
        } else {
            priceComp =  Double.compare(p2.getPrice(), p1.getPrice());
        }
        if (priceComp != 0) {
            return priceComp;
        }
        return p1.getName().compareTo(p2.getName());
    }
}