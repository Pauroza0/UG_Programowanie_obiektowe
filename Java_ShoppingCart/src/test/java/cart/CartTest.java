package cart;

import org.example.Cart;

import org.example.Product.PriceComparator;
import org.example.Product.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartTest {
    private Cart cart;

    @BeforeEach
    public void setUp() {
        Product prod1 = new Product("X001", "Kubek", 5.00);
        Product prod2 = new Product("X002", "Kalendarz", 10.00);
        Product prod3 = new Product("X003", "Czekoladowa Moneta", 10.00);
        Product prod4 = new Product("X004", "Taboret", 100.00);
        Product prod5 = new Product("X005", "Karty do Tarota", 75.00);
        Product[] products = {prod1, prod2, prod3, prod4, prod5};
        cart = new Cart(products);
    }

    @Test
    public void findMostExpensive() {
        assertEquals("X004", cart.getMostExpensiveProduct().getCode());
    }

    @Test void findNMostExpensive() {
        Product[] expected = {
                cart.getProducts()[3],
                cart.getProducts()[4]
        };

        Product[] result = cart.findNMostExpensiveProducts(2);

        assertArrayEquals(expected, result);

    }

    @Test
    public void findNCheapest(){
        Product[] expected = {
                cart.getProducts()[0],
                cart.getProducts()[2],
                cart.getProducts()[1],
        };

        Product[] result = cart.findNCheapestProducts(3);

        assertArrayEquals(expected, result);

    }
    @Test
    public void testTotal() {
        assertEquals(200.00, cart.calculateTotal());
    }
}
