package org.example;

import org.example.Product.NameComparator;
import org.example.Product.PriceComparator;
import org.example.Product.Product;

import java.util.Arrays;
import java.util.Objects;

public class Cart {
    private Product[] products;
    private int productCount;

    public Cart() {
        this.products = new Product[0];
        this.productCount = 0;
    }

    public Cart(Product[] products) {
        this.products = products;
        productCount = products.length;
    }

    public void addProduct(Product product) {
        ensureCapacity();
        products[productCount++] = product;
        Arrays.sort(products);
    }

    private void ensureCapacity() {products = Arrays.copyOf(products, products.length + 1);
    }

    public Double calculateTotal() {
        double total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getDiscountPrice();
        }
        return total;
    }
    public Product getCheapestProduct() {
        return findNCheapestProducts(1)[0];
    }

    public Product getMostExpensiveProduct() {
        return findNMostExpensiveProducts(1)[0];
    }

    public void sortByName() {
        Arrays.sort(products, new NameComparator());
    }

    public Product[] findNCheapestProducts(int n) {
        Product[] sortedProducts = Arrays.copyOf(products, productCount);
        Arrays.sort(sortedProducts, new PriceComparator(true));
        return Arrays.copyOf(sortedProducts, Math.min(n, productCount));
    }
    public Product[] findNMostExpensiveProducts(int n) {
        Product[] sortedProducts = Arrays.copyOf(products, productCount);
        Arrays.sort(sortedProducts, new PriceComparator(false));
        return Arrays.copyOf(sortedProducts, Math.min(n, productCount));
    }
    public Product findProduct(String code) {
        for (Product product : products){
            if (Objects.equals(product.getCode(), code)) {
                return product;
            }
        }
        return null;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getProductCount() {
        return productCount;
    }
}
