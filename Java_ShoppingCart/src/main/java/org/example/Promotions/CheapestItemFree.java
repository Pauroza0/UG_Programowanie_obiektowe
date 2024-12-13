package org.example.Promotions;

import org.example.Cart;

public class CheapestItemFree implements Promotion{
    private Cart cart;

    public CheapestItemFree(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean isEligible() {
        return cart.getProductCount() > 2;
    }

    @Override
    public void apply() {
        cart.getCheapestProduct().setDiscountPrice(100);
    }
}
