package com.example.demo.service;

import com.example.demo.model.product.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final String baseImageUrl;
    private final List<Product> products;

    public ProductService(@Value("${app.base-url}") final String baseImageUrl) {
        this.baseImageUrl = baseImageUrl + "/images/products/";

        // initialize products after baseImageUrl is set
        this.products = List.of(
                new Product(
                        "PT5000",
                        "Noblelift AC55 Pallet Truck",
                        new BigDecimal("699.00"),
                        "USD",
                        // use existing image filenames from resources/static/images/products
                        this.baseImageUrl + "pallettruck.png",
                        "5,500 lb capacity manual pallet truck",
                        "in_stock",
                        "pallet truck with ergonomic handle and durable steel frame",
                        null,
                        null,
                        null
                ),
                new Product(
                        "PT4500",
                        "Industrial Pallet Jack 4500 lb",
                        new BigDecimal("589.00"),
                        "USD",
                        this.baseImageUrl + "pallettruck.png",
                        "Heavy-duty pallet jack for warehouses",
                        "in_stock",
                        "robust pallet jack with high load capacity and easy maneuverability",
                        null,
                        null,
                        null
                ),
                new Product(
                        "PT6000",
                        "Lightweight Pallet Truck 2000 lb",
                        new BigDecimal("349.00"),
                        "USD",
                        this.baseImageUrl + "pallettruck.png",
                        "Compact pallet truck for light-duty use",
                        "in_stock",
                        "lightweight design ideal for small warehouses and retail environments",
                        null,
        null,null
                ),
                new Product(
                        "BOX5000",
                        "White Corrugated Boxes - 5000 ct",
                        new BigDecimal("199.00"),
                        "USD",
                        // use existing image filenames from resources/static/images/products
                        this.baseImageUrl + "box.jpg",
                        "5000 count of white corrugated boxes",
                        "in_stock",
                        "sturdy and reliable boxes for shipping and storage",
                        "100",
                        "150",
                        "200"
                ),
                new Product(
                        "BOX4500",
                        "Brown Shipping Boxes - 4500 ct",
                        new BigDecimal("589.00"),
                        "USD",
                        this.baseImageUrl + "box.jpg",
                        "4500 count of brown shipping boxes",
                        "in_stock",
                        "durable boxes suitable for various shipping needs",
                        "50",
                        "50",
                        "50"
                ),
                new Product(
                        "BOX6000",
                        "small corrugated boxes - 6000 ct",
                        new BigDecimal("349.00"),
                        "USD",
                        this.baseImageUrl + "box.jpg",
                        "6000 count of small corrugated boxes",
                        "in_stock",
                        "perfect for small items and retail packaging",
                        "10",
                        "10","10"
                ),
                new Product(
                        "TL5000",
                        "Jumbo toilet rolls - 50 ct",
                        new BigDecimal("50.00"),
                        "USD",
                        // use existing image filenames from resources/static/images/products
                        this.baseImageUrl + "tissuerolls.jpg",
                        "50 rolls of jumbo toilet paper",
                        "in_stock",
                        "sturdy and reliable boxes for shipping and storage",
                        "100",
                        "150",
                        "200"
                ),
                new Product(
                        "TL4500",
                        "Small toilet rolls - 45 ct",
                        new BigDecimal("45.00"),
                        "USD",
                        this.baseImageUrl + "tissuerolls.jpg",
                        "4500 count of brown shipping boxes",
                        "in_stock",
                        "small toilet rolls suitable for travel",
                        "50",
                        "50",
                        "50"
                ),
                new Product(
                        "TL6000",
                        "soft toilet rolls - 90 ct",
                        new BigDecimal("60.00"),
                        "USD",
                        this.baseImageUrl + "tissuerolls.jpg",
                        "6000 count of small corrugated boxes",
                        "in_stock",
                        "perfect for economic buy",
                        "10",
                        "10","10"
                ),
                new Product(
                        "SCL5000",
                        "Small scale",
                        new BigDecimal("170.00"),
                        "USD",
                        this.baseImageUrl + "smallscale.jpg",
                        "Kitchen scale with 5kg capacity",
                        "in_stock",
                        "perfect for countering small items and retail packaging",
                        "100",
                        "10","10"
                ),
                new Product(
                        "SCL4000",
                        "Retail scale",
                        new BigDecimal("300.00"),
                        "USD",
                        this.baseImageUrl + "scale.jpg",
                        "Retail scale with 15kg capacity",
                        "in_stock",
                        "perfect for retail packaging",
                        "150",
                        "10","10"
                ),
                new Product(
                        "SCL3000",
                        "Standard scale",
                        new BigDecimal("500.00"),
                        "USD",
                        this.baseImageUrl + "stdscale.jpg",
                        "Industrial scale with 30kg capacity",
                        "in_stock",
                        "perfect for industrial use and warehouses",
                        "200",
                        "10","10"
                )

        );
    }

    public List<Product> search(String query, int limit) {
        int safeLimit = Math.max(1, limit);

        if (query == null || query.isBlank()) {
            return products.stream()
                    .limit(safeLimit)
                    .collect(Collectors.toList());
        }

        String q = query.toLowerCase();

        return products.stream()
                .filter(p ->
                        p.getShortDescription().toLowerCase().contains(q) ||
                                p.getName().toLowerCase().contains(q))
                .limit(safeLimit)
                .collect(Collectors.toList());
    }

    public Optional<Product> getBySku(String sku) {
        if (sku == null || sku.isBlank()) {
            return Optional.empty();
        }

        return products.stream()
                .filter(p -> p.getSku().equalsIgnoreCase(sku))
                .findFirst();
    }
}
