package org.example.Promotions;

import org.example.Product.Product;

import java.util.List;

public class PromotionManager {
    List<Promotion> activePromotions;

    public PromotionManager(List<Promotion> activePromotions) {
        this.activePromotions = activePromotions;
    }

    public void applyPromotions() {
        for (Promotion promotion : activePromotions) {
            if (promotion.isEligible()) {
                promotion.apply();
            }
        }
    }
}
