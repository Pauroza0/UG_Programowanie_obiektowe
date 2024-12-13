package cart;

import org.example.Cart;
import org.example.Product.Product;
import org.example.Promotions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PromotionTest {
    Product prod1 = new Product("X001", "Kubek", 5.00);
    Product prod2 = new Product("X002", "Kalendarz", 10.00);
    Product prod3 = new Product("X003", "Czekoladowa Moneta", 10.00);
    Product prod4 = new Product("X004", "Taboret", 100.00);
    Product prod5 = new Product("X005", "Karty do Tarota", 75.00);
    Product prod6 = new Product("X006", "Energetyk", 250.00);
    Product[] products = {prod1, prod2, prod3, prod4, prod5, prod6};
    Cart cart = new Cart(products);
    Promotion fivePerOffWhenTotalGt300 = new WholeDiscount(cart, 5);
    Promotion getCheapestProductForFreeWhenBuying3 = new CheapestItemFree(cart);
    Product freeCup = new Product("X999", "Firmowy Kubek", 0.00);
    Promotion getFreeCupWhenTotalOver200 = new FreeItemPromotion(cart, freeCup);
    Promotion coupon30pOff = new CouponDiscount(cart, "X005", 30);
    List<Promotion> active = new ArrayList<>();

    @Test
    public void wholeDiscount(){
        active.add(fivePerOffWhenTotalGt300);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Double result = cart.calculateTotal();
        Assertions.assertEquals(427.5, result);

        for(Product product : cart.getProducts()) {
            Assertions.assertEquals(product.getPrice() * 0.95, product.getDiscountPrice());
        }
    }

    @Test
    public void testCheapestFree(){
        active.add(getCheapestProductForFreeWhenBuying3);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Double result = cart.calculateTotal();
        Assertions.assertEquals(445.00, result);
        Assertions.assertEquals(0, prod1.getDiscountPrice());
    }

    @Test
    public void testFreeCup(){
        active.add(getFreeCupWhenTotalOver200);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Assertions.assertEquals(7, cart.getProductCount());
        Assertions.assertTrue(Arrays.asList(cart.getProducts()).contains(freeCup));
    }

    @Test
    public void testCoupon(){
        active.add(coupon30pOff);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Double result = cart.calculateTotal();
        Assertions.assertEquals(52.5, prod5.getDiscountPrice());
        Assertions.assertEquals(427.5, result);
    }
    @Test
    public void testAll(){
        active.add(coupon30pOff);
        active.add(getCheapestProductForFreeWhenBuying3);
        active.add(fivePerOffWhenTotalGt300);
        active.add(getFreeCupWhenTotalOver200);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Double result = cart.calculateTotal();
        Assertions.assertEquals(401.375, result);
        Assertions.assertEquals(7, cart.getProductCount());
    }
    @Test
    public void shouldNotApply(){
        Product[] prod = {prod5, prod6};
        Cart cart2 = new Cart(prod);
        Promotion coupon = new CouponDiscount(cart2, "X006", 50);
        Promotion noCup = new FreeItemPromotion(cart2, freeCup);
        Promotion noCheapestItem= new CheapestItemFree(cart2);
        Promotion noDiscount = new WholeDiscount(cart2, 10);
        active.add(coupon);
        active.add(noCup);
        active.add(noCheapestItem);
        active.add(noDiscount);
        PromotionManager promotionManager = new PromotionManager(active);
        promotionManager.applyPromotions();
        Double result = cart2.calculateTotal();
        Assertions.assertEquals(200.00, result);
        Assertions.assertEquals(2, cart2.getProductCount());
    }
}


