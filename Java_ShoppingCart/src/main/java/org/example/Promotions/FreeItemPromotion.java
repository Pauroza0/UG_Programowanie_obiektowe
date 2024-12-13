package org.example.Promotions;

import org.example.Cart;
import org.example.Product.Product;
import org.example.Promotions.Promotion;

public class FreeItemPromotion implements Promotion {
    private Cart cart;
    private Product product;

    public FreeItemPromotion(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    @Override
    public boolean isEligible() {
        return cart.calculateTotal() > 200.00;
    }

    @Override
    public void apply() {
        cart.addProduct(product);
    }
}
