package com.example.demo.model.checkout;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class CheckoutSession {

    // Core identifiers
    private UUID checkoutSessionId;
    private UUID cartId;

    // Cart contents
    private List<CheckoutItem> items;

    // Pricing
    private BigDecimal subtotal;
    private BigDecimal shippingCost;
    private BigDecimal tax;
    private BigDecimal total;

    // Shipping
    private ShipToAddress shippingAddress;
    private ShipToOption selectedShipping;

    // Payment
    private String paymentToken;

    // Order
    private String orderId;

    // Lifecycle
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    // ----- Constructors -----

    public CheckoutSession() {
        // Required for Jackson
    }

    public CheckoutSession(
            UUID checkoutSessionId,
            UUID cartId,
            List<CheckoutItem> items,
            BigDecimal subtotal,
            String status
    ) {
        this.checkoutSessionId = checkoutSessionId;
        this.cartId = cartId;
        this.items = items;
        this.subtotal = subtotal;
        this.status = status;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    // ----- Getters -----

    public UUID getCheckoutSessionId() {
        return checkoutSessionId;
    }

    public UUID getCartId() {
        return cartId;
    }

    public List<CheckoutItem> getItems() {
        return items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public ShipToAddress getShippingAddress() {
        return shippingAddress;
    }

    public ShipToOption getSelectedShipping() {
        return selectedShipping;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    // ----- Explicit setters (NO hidden behavior) -----

    public void setShippingAddress(ShipToAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        touch();
    }

    public void setSelectedShipping(ShipToOption selectedShipping) {
        this.selectedShipping = selectedShipping;
        this.shippingCost = selectedShipping.getCost();
        recalculateTotals();
        touch();
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
        touch();
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        touch();
    }

    public void setStatus(String status) {
        this.status = status;
        touch();
    }

    // ----- Helpers -----

    private void recalculateTotals() {
        // Simple mock tax calculation (10%)
        this.tax = subtotal.multiply(new BigDecimal("0.10"));

        this.total = subtotal
                .add(shippingCost != null ? shippingCost : BigDecimal.ZERO)
                .add(tax != null ? tax : BigDecimal.ZERO);
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }
}
