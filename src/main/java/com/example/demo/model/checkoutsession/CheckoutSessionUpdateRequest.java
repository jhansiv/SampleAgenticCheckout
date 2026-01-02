package com.example.demo.model.checkoutsession;

import io.micrometer.common.lang.Nullable;

public interface CheckoutSessionUpdateRequest {
    String getViaId();

    @Nullable
    ShipToAddress getAddress();
}
