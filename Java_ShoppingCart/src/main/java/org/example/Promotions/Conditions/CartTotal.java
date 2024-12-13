package org.example.Promotions.Conditions;

public class CartTotal implements Conditions {
    private int condition;

    @Override
    public boolean isEligible() {
        return false;
    }
}
