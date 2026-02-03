package com.example.demo.model.checkout;

import java.util.List;

public class CreateCheckoutRequest {

    private List<CartItemRequest> items;

    public List<CartItemRequest> getItems() {
        return items;
    }
}
