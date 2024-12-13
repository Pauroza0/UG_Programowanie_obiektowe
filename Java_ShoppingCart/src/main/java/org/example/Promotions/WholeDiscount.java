package org.example.Promotions;

import org.example.Cart;
import org.example.Product.Product;

public class WholeDiscount implements Promotion {
    private Cart cart;
    private int amount;

    public WholeDiscount(Cart cart, int amount) {
        this.cart = cart;
        this.amount = amount;
    }

    @Override
    public boolean isEligible() {
        return cart.calculateTotal() > 300.00;
    }

    @Override
    public void apply() {
        for (Product product : cart.getProducts()) {
            product.setDiscountPrice(amount);
        }
    }
}
