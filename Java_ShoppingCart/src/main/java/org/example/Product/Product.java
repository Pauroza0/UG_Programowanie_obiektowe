package org.example.Product;

public class Product implements Comparable<Product> {
    private String code;
    private String name;
    private double price;
    private double discountPrice;
    private boolean coupon;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
        this.coupon = false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int amount) {
        this.discountPrice = discountPrice - (discountPrice * ((double) amount /100));
    }

    public boolean hasCoupon(){
        return coupon;
    }

    public void setCoupon() {
        coupon = true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        int priceComparison = Double.compare(this.price, o.price);
        if (priceComparison != 0) {
            return Double.compare(o.price, this.price);
        }
        return this.name.compareTo(o.getName());
    }
}
