package org.example.Promotions;

import org.example.Cart;
import org.example.Product.Product;
import org.example.Promotions.Promotion;

public class CouponDiscount implements Promotion {
    Cart cart;
    int amount;
    Product product;

    public CouponDiscount(Cart cart, String productCode, int amount) {
        this.cart = cart;
        this.product = cart.findProduct(productCode);
        this.amount = amount;
    }

    @Override
    public boolean isEligible() {
        return product != null && !product.hasCoupon();
    }

    @Override
    public void apply() {
        product.setCoupon();
        product.setDiscountPrice(amount);
    }
}
