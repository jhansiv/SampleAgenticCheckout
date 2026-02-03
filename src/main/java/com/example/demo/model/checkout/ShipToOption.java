package com.example.demo.model.checkout;

import java.math.BigDecimal;

public class ShipToOption {

    private String id;
    private String label;
    private int estimatedDays;
    private BigDecimal cost;

    public ShipToOption(String id, String label, int estimatedDays, BigDecimal cost) {
        this.id = id;
        this.label = label;
        this.estimatedDays = estimatedDays;
        this.cost = cost;
    }

    public String getId() { return id; }
    public String getLabel() { return label; }
    public int getEstimatedDays() { return estimatedDays; }
    public BigDecimal getCost() { return cost; }
}

