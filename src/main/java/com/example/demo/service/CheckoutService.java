package com.example.demo.service;


import com.example.demo.model.checkout.CartItemRequest;
import com.example.demo.model.checkout.CheckoutItem;
import com.example.demo.model.checkout.CheckoutSession;
import com.example.demo.model.checkout.ShipToAddress;
import com.example.demo.model.checkout.ShipToOption;
import com.example.demo.model.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CheckoutService {

    private final ProductService productService;
    private final Map<UUID, CheckoutSession> sessions = new ConcurrentHashMap<>();

    public CheckoutService(ProductService productService) {
        this.productService = productService;
    }

    public CheckoutSession createSession(List<CartItemRequest> items) {
        UUID sessionId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();

        BigDecimal subtotal = BigDecimal.ZERO;
        List<CheckoutItem> checkoutItems = new ArrayList<>();

        for (CartItemRequest req : items) {
            Product p = productService.getBySku(req.getSku()).orElseThrow();
            CheckoutItem item = new CheckoutItem(
                    p.getSku(), p.getName(), req.getQuantity(), p.getPrice()
            );
            checkoutItems.add(item);
            subtotal = subtotal.add(item.getLineTotal());
        }

        CheckoutSession session = new CheckoutSession(
                sessionId, cartId, checkoutItems, subtotal, "CREATED"
        );

        sessions.put(sessionId, session);
        return session;
    }

    public CheckoutSession addAddress(UUID id, ShipToAddress address) {
        CheckoutSession s = get(id);
        s.setShippingAddress(address);
        s.setStatus("ADDRESS_ADDED");
        return s;
    }

    public List<ShipToOption> getShipToOption(UUID id) {
        CheckoutSession s = get(id);
        return List.of(
                new ShipToOption("STD", "Standard (5–7 days)", 7, new BigDecimal("45.00")),
                new ShipToOption("EXP", "Express (2–3 days)", 3, new BigDecimal("85.00"))
        );
    }

    public CheckoutSession selectShipping(UUID id, String optionId) {
        CheckoutSession s = get(id);
        ShipToOption option = optionId.equals("EXP")
                ? new ShipToOption("EXP", "Express (2–3 days)", 3, new BigDecimal("85.00"))
                : new ShipToOption("STD", "Standard (5–7 days)", 7, new BigDecimal("45.00"));

        s.setSelectedShipping(option);
        s.setStatus("SHIPPING_SELECTED");
        return s;
    }

    public CheckoutSession confirm(UUID id) {
        CheckoutSession s = get(id);
        s.setOrderId("ORD-" + UUID.randomUUID().toString().substring(0, 8));
        s.setStatus("CONFIRMED");
        return s;
    }

    public CheckoutSession get(UUID id) {
        CheckoutSession s = sessions.get(id);
        if (s == null) throw new RuntimeException("Session not found");
        return s;
    }
}
