package org.example;

public class ImportReceipt {
    String importId, importDate, supplier, product;
    int quantity;
    double importPrice, totalPrice;

    public ImportReceipt(String importId, String importDate, String supplier, String product, int quantity, double importPrice, double totalPrice) {
        this.importId = importId;
        this.importDate = importDate;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ImportReceipt{" +
                "importId='" + importId + '\'' +
                ", importDate='" + importDate + '\'' +
                ", supplier='" + supplier + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", importPrice=" + importPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
