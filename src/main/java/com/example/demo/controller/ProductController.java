package com.example.demo.controller;

import com.example.demo.model.product.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Product search endpoint
     * Used by the shopping agent for discovery
     *
     * Example:
     * GET /products/search?q=pallet&limit=5
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, List<Product>>> search(
            @RequestParam String q,
            @RequestParam(defaultValue = "5") int limit) {

        List<Product> products = productService.search(q, limit);
        return ResponseEntity.ok(Map.of("products", products));
    }

    /**
     * Product lookup by SKU
     * Used for "Buy AC55"
     *
     * Example:
     * GET /products/AC55
     */
    @GetMapping("/{sku}")
    public ResponseEntity<Product> getBySku(@PathVariable String sku) {
        return productService.getBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
