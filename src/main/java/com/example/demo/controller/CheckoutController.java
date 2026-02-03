package com.example.demo.controller;

import com.example.demo.model.checkout.CheckoutSession;
import com.example.demo.model.checkout.CreateCheckoutRequest;
import com.example.demo.model.checkout.ShipToAddress;
import com.example.demo.model.checkout.ShipToOption;
import com.example.demo.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/checkout/session")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(final CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<CheckoutSession> create(@RequestBody CreateCheckoutRequest req) {
        return ResponseEntity.status(201).body(
                checkoutService.createSession(req.getItems())
        );
    }

    @PostMapping("/{id}/address")
    public CheckoutSession addAddress(
            @PathVariable UUID id,
            @RequestBody ShipToAddress address) {
        return checkoutService.addAddress(id, address);
    }

    @GetMapping("/{id}/shipping")
    public List<ShipToOption> shippingOptions(@PathVariable UUID id) {
        return checkoutService.getShipToOption(id);
    }

    @PostMapping("/{id}/shipping")
    public CheckoutSession selectShipping(
            @PathVariable UUID id,
            @RequestParam String optionId) {
        return checkoutService.selectShipping(id, optionId);
    }

    @PostMapping("/{id}/confirm")
    public CheckoutSession confirm(@PathVariable UUID id) {
        return checkoutService.confirm(id);
    }

    @GetMapping("/{id}")
    public CheckoutSession get(@PathVariable UUID id) {
        return checkoutService.get(id);
    }
}
