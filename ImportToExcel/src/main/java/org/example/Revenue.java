package org.example;

public class Revenue {
    String date, orderId, customer, paymentMethod;
    double totalPrice;

    public Revenue(String date, String orderId, String customer, double totalPrice, String paymentMethod) {
        this.date = date;
        this.orderId = orderId;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "date='" + date + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customer='" + customer + '\'' +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

}

