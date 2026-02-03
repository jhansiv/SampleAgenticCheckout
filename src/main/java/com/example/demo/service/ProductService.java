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
                        "in_stock"
                ),
                new Product(
                        "PT4500",
                        "Industrial Pallet Jack 4500 lb",
                        new BigDecimal("589.00"),
                        "USD",
                        this.baseImageUrl + "box.jpg",
                        "Heavy-duty pallet jack for warehouses",
                        "in_stock"
                ),
                new Product(
                        "PT6000",
                        "Lightweight Pallet Truck 2000 lb",
                        new BigDecimal("349.00"),
                        "USD",
                        this.baseImageUrl + "tissuerolls.jpg",
                        "Compact pallet truck for light-duty use",
                        "in_stock"
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
                        p.getSku().toLowerCase().contains(q) ||
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
