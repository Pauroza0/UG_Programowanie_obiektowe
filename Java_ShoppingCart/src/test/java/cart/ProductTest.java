package cart;

import org.example.Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductTest {
    @BeforeEach
    public void setUp(){
    }

    @Test
    public void defaultSort() {
        Product prod1 = new Product("X001", "Kubek", 5.00);
        Product prod2 = new Product("X002", "Kalendarz", 10.00);
        Product prod3 = new Product("X003", "Czekoladowa Moneta", 10.00);
        Product prod4 = new Product("X004", "Taboret", 100.00);
        Product prod5 = new Product("X005", "Karty do Tarota", 75.00);
        Product[] sorted = {prod4, prod5, prod3, prod2, prod1};
        Product[] products = {prod1, prod2, prod3, prod4, prod5};
        Arrays.sort(products);

        assertArrayEquals(sorted, products);
    }
}
