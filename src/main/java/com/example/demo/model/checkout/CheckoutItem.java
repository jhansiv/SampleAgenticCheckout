package com.example.demo.model.checkout;

import java.math.BigDecimal;

public class CheckoutItem {

    private String sku;
    private String name;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;

    public CheckoutItem() {}

    public CheckoutItem(String sku, String name, int quantity, BigDecimal unitPrice) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getLineTotal() { return lineTotal; }
}
