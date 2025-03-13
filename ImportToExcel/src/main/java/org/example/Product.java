package org.example;

public class Product {
    private String productId, name, category, supplier;
    private int importPrice, salePrice, stock;

    public Product(String productId, String name, String category, int importPrice, int salePrice,
                   int stock, String supplier){
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.importPrice = importPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", importPrice=" + importPrice +
                ", salePrice=" + salePrice +
                ", stock=" + stock +
                ", supplier='" + supplier + '\'' +
                '}';
    }


}