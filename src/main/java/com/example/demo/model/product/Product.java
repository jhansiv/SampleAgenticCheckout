package com.example.demo.model.product;

import java.math.BigDecimal;

public class Product {

    private String sku;
    private String name;
    private BigDecimal price;
    private String currency;
    private String imageUrl;
    private String shortDescription;
    private String availability;

    // Required by Jackson
    public Product() {
    }

    public Product(String sku,
                   String name,
                   BigDecimal price,
                   String currency,
                   String imageUrl,
                   String shortDescription,
                   String availability) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.availability = availability;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

